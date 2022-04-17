package com.baole.common.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baole.common.constants.LoggerConstant;
import com.baole.search.dto.es.request.EsScrollRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.IndexOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.data.elasticsearch.core.query.UpdateResponse;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * elasticsearch 操作命令封装
 */
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class ElasticsearchCommand {

    private final ElasticsearchRestTemplate elasticsearchOperations;

    @Autowired
    private ElasticsearchRestService elasticsearchRestService;


    public ElasticsearchCommand(ElasticsearchRestTemplate elasticsearchOperations) {
        this.elasticsearchOperations = elasticsearchOperations;
    }

    public boolean indexCreate(Class<?> clazz) {

        IndexOperations indexOperations = elasticsearchOperations.indexOps(clazz);

        return indexOperations.create() && indexOperations.putMapping();
    }

    public String getIndexName(Class<?> clazz) {

        IndexOperations indexOperations = elasticsearchOperations.indexOps(clazz);

        return indexOperations.getIndexCoordinates().getIndexName();
    }

    public boolean addAliases(String indexName, String... aliasNames) {

        IndexOperations indexOperations = elasticsearchOperations.indexOps(IndexCoordinates.of(indexName));

        AliasActions aliasActions = new AliasActions();
        for (String aliasName : aliasNames) {

            aliasActions.add(new AliasAction.Add(AliasActionParameters.builder().withIndices(indexName).withAliases(aliasName).build()));
        }

        return indexOperations.alias(aliasActions);
    }

    public <T> Iterable<T> save(String indexName, Iterable<T> entities) {

        return elasticsearchOperations.save(entities, IndexCoordinates.of(indexName));
    }

    public UpdateResponse update(String indexName, String id, Map<String, Object> fieldsUpdate) {

        Document document = Document.from(fieldsUpdate);

        UpdateQuery updateQuery = UpdateQuery.builder(id).withDocument(document).build();

        return elasticsearchOperations.update(updateQuery, IndexCoordinates.of(indexName));
    }

    public <T> T getById(String indexName, String id, Class<T> clazz) {

        return elasticsearchOperations.get(id, clazz, IndexCoordinates.of(indexName));
    }

    public <T> SearchHits<T> query(String indexName, NativeSearchQuery nativeSearchQuery, Class<T> clazz) {

        log.info("ElasticsearchCommand query info... \n {}", print(indexName, nativeSearchQuery));
        return elasticsearchOperations.search(nativeSearchQuery, clazz, IndexCoordinates.of(indexName));
    }

    public <T> SearchHits<T> queryByScroll(String indexName, NativeSearchQuery nativeSearchQuery, Class<T> clazz, long scrollTimeInMillis, @Nullable String scrollId) {
        SearchScrollHits<T> searchHits;
        if (scrollId == null) {
            log.info("ElasticsearchCommand queryByScroll info... \n {}", print(indexName, nativeSearchQuery, scrollTimeInMillis));
            searchHits= elasticsearchOperations
                    .searchScrollStart(scrollTimeInMillis, nativeSearchQuery, clazz, IndexCoordinates.of(indexName));
        }else{
            log.info("ElasticsearchCommand queryByScroll info... indexName: {} \n {}", indexName, print(scrollTimeInMillis, scrollId));
            searchHits= elasticsearchOperations.searchScrollContinue(scrollId, scrollTimeInMillis, clazz,
                    IndexCoordinates.of(indexName));
        }
        if(StringUtils.isNotBlank(scrollId) && CollectionUtils.isEmpty(searchHits.getSearchHits())
                && searchHits.getAggregations()==null){
            elasticsearchOperations.searchScrollClear(Collections.singletonList(scrollId));
        }
        return searchHits;
    }
    public long queryCount(String indexName, NativeSearchQuery nativeSearchQuery, Class<?> clazz) {

        log.info("ElasticsearchCommand query info... \n {}", print(indexName, nativeSearchQuery));
        return elasticsearchOperations.count(nativeSearchQuery, clazz, IndexCoordinates.of(indexName));
    }

    public <T> PageResult<T> searchAfter(String indexName, NativeSearchQuery nativeSearchQuery, Class<T> clazz,
            String searchAfter) {
        String searchAfterDsl =
                StringUtils.isBlank(searchAfter) ? "\n" : ",\n" + "\"search_after\":[" + searchAfter + "]";
        String dsl = "{" + queryToString(nativeSearchQuery) + searchAfterDsl + "}";
        JSONObject search = elasticsearchRestService.search(new SearchRequest(indexName, dsl));
        PageResult<T> result = elasticsearchRestService.parseDocumentSearchAfter(search, clazz);
        return result;
    }

    public <T> EsResponseDTO<T> search(String indexName, NativeSearchQuery nativeSearchQuery, Class<T> clazz) {
        String dsl = "{" + queryToString(nativeSearchQuery) + "}";
        JSONObject search = elasticsearchRestService.search(new SearchRequest(indexName, dsl));
        EsResponseDTO<T> result = elasticsearchRestService.parseEsDocument(search, clazz);
        return result;
    }

    public <T> EsResponseDTO<T> searchScroll(String indexName, NativeSearchQuery nativeSearchQuery, Class<T> clazz,
            EsScrollRequestDTO esScrollRequestDTO) {
        String dsl = "{" + queryToString(nativeSearchQuery) + "}";
        JSONObject search = elasticsearchRestService
                .scrollSearch(new SearchRequest(indexName, dsl, esScrollRequestDTO));
        EsResponseDTO<T> result = elasticsearchRestService.parseEsDocument(search, clazz);
        return result;
    }

    private String queryToString(NativeSearchQuery nativeSearchQuery) {

        StringBuilder s = new StringBuilder();

        s.append("\"query\": ").append(nativeSearchQuery.getQuery());
        if (!CollectionUtils.isEmpty(nativeSearchQuery.getAggregations())) {

            s.append(",\"aggs\":{ ");

            List<?> list = nativeSearchQuery.getAggregations();
            for (int i = 0; i < list.size(); i++) {

                String agg = list.get(i).toString();

                if (i == list.size() - 1) {
                    s.append(agg, 1, agg.length() - 1).append("}");
                } else {
                    s.append(agg, 1, agg.length() - 1).append(",");
                }
            }
        }

        if (!CollectionUtils.isEmpty(nativeSearchQuery.getElasticsearchSorts())) {

            s.append(",\"sort\": ").append(nativeSearchQuery.getElasticsearchSorts().toString());
        }

        if (nativeSearchQuery.getPageable().isPaged()) {

            s.append(",\"from\": ").append(nativeSearchQuery.getPageable().getOffset());
            s.append(",\"size\": ").append(nativeSearchQuery.getPageable().getPageSize());
        }

        if (nativeSearchQuery.getSourceFilter() != null) {

            s.append(",\"_source\": ").append(JSON.toJSONString(nativeSearchQuery.getSourceFilter()));
        }

        return s.toString();
    }

    private String print(String indexName, NativeSearchQuery nativeSearchQuery) {

        if (indexName == null || nativeSearchQuery == null) {
            return null;
        }

        return "GET " + indexName + "/_search\n{" +
                queryToString(nativeSearchQuery) +
                "}";
    }

    private String print(String indexName, NativeSearchQuery nativeSearchQuery, long scrollTimeInMillis) {

        if (indexName == null || nativeSearchQuery == null) {
            return null;
        }

        return "GET " + indexName + "/_search?scroll=" + scrollTimeInMillis + "ms\n{" +
                queryToString(nativeSearchQuery) +
                "}";
    }

    private String print(long scrollTimeInMillis, String scrollId) {

        if (scrollId == null) {
            return null;
        }

        return "GET /_search/scroll\n{" +
                "\"scroll\":\"" + scrollTimeInMillis + "ms\",\"scroll_id\":\"" +
                scrollId +
                "\"}";
    }
}
