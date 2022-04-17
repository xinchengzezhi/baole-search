package com.baole.common.elasticsearch;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * @Description:
 * @Author: baole.liang
 * @Date: 2021/9/29
 */
@Component("elasticsearchRestService")
public class ElasticsearchRestService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchRestService.class);

    @Resource
    private ElasticsearchRestClient elasticsearchRestClient;

    public JSONObject search(SearchRequest searchRequest) {
        try {
            LOGGER.info("查询语句: index={}, dsl=\n{}", searchRequest.getIndex(), searchRequest.getDsl());
            Request request = new Request("POST", searchRequest.getIndex() + "/_search?track_total_hits=true"
                    + "&typed_keys=true&max_concurrent_shard_requests=5&ignore_unavailable=false&expand_wildcards=open&allow_no_indices=true&ignore_throttled=true&request_cache=true&search_type=dfs_query_then_fetch&batched_reduce_size=512&ccs_minimize_roundtrips=true");
            request.setJsonEntity(searchRequest.getDsl());
            long startTime = System.currentTimeMillis();
            Response response = elasticsearchRestClient.getRestClient().performRequest(request);
            //打点监控
            JSONObject jsonObject = JSONObject
                    .parseObject(EntityUtils.toString(response.getEntity()), JSONObject.class);
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("查询文档异常, request=" + JSONObject.toJSONString(searchRequest), e);
            return null;
        }
    }

    public JSONObject scrollSearch(SearchRequest searchRequest) {
        try {
            if(searchRequest.getEsScrollRequestDTO().getScrollTimeInMillis()<1){
                //默认5分钟
                searchRequest.getEsScrollRequestDTO().setScrollTimeInMillis(5*60*1000);
            }
            Request request;
            if (StringUtils.isNotBlank(searchRequest.getEsScrollRequestDTO().getScrollId())) {
                request = new Request("POST", "_search/scroll");
                request.setJsonEntity("{\n" +
                        " \"scroll\":\"" + searchRequest.getEsScrollRequestDTO().getScrollTimeInMillis() + "ms\",\n" +
                        " \"scroll_id\":\"" + searchRequest.getEsScrollRequestDTO().getScrollId() + "\"\n" +
                        "}");
            } else {
                request = new Request("POST", searchRequest.getIndex() + "/_search");
                request.addParameter("scroll", searchRequest.getEsScrollRequestDTO().getScrollTimeInMillis() + "ms");
                request.setJsonEntity(searchRequest.getDsl());
            }

            Response response = elasticsearchRestClient.getRestClient().performRequest(request);

            JSONObject jsonObject = JSONObject
                    .parseObject(EntityUtils.toString(response.getEntity()), JSONObject.class);
            JSONArray jsonArray = jsonObject.getJSONObject("hits").getJSONArray("hits");

            if (jsonArray == null || jsonArray.size() == 0 || searchRequest.getEsScrollRequestDTO().isFinish()) {
                String scrollId = jsonObject.getString("_scroll_id");
                deleteScroll(scrollId);
            }
            return jsonObject;
        } catch (Exception e) {
            LOGGER.error("查询文档异常, request=" + JSONObject.toJSONString(searchRequest), e);
            return null;
        }
    }

    private Response deleteScroll(String scrollId) throws IOException {
        if (StringUtils.isBlank(scrollId)) {
            return null;
        }
        Request request = new Request("DELETE", "_search/scroll");
        request.setJsonEntity("{\n" +
                "    \"scroll_id\" : \"" + scrollId + "\"\n" +
                "}");
        return elasticsearchRestClient.getRestClient().performRequest(request);
    }

    public <T> EsResponseDTO<T> parseEsDocument(JSONObject jsonObject, Class<T> type) {
        EsResponseDTO<T> pageResult = new EsResponseDTO<>();
        pageResult.setTotalHits(0);
        pageResult.setResponseList(Collections.emptyList());
        if (jsonObject == null) {
            return pageResult;
        }
        pageResult.setScrollId(jsonObject.getString("_scroll_id"));
        JSONObject searchHits = jsonObject.getJSONObject("hits");
        if (searchHits == null) {
            return pageResult;
        }
        Long total = searchHits.getJSONObject("total").getLong("value");
        if (total > 0) {
            List<T> documents = new ArrayList<>();
            JSONArray hits = searchHits.getJSONArray("hits");
            for (int i = 0; i < hits.size(); i++) {
                T spuDocument = hits.getJSONObject(i).getObject("_source", type);
                documents.add(spuDocument);
            }
            pageResult.setTotalHits(total.intValue());
            pageResult.setResponseList(documents);

            return pageResult;
        }
        return pageResult;
    }

    public <T> PageResult<T> parseDocument(JSONObject jsonObject, Class<T> type) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotalCount(0);
        pageResult.setDetails(Collections.emptyList());
        if (jsonObject == null) {
            return pageResult;
        }

        JSONObject searchHits = jsonObject.getJSONObject("hits");
        if (searchHits == null) {
            return pageResult;
        }
        Long total = searchHits.getJSONObject("total").getLong("value");
        if (total > 0) {
            List<T> documents = new ArrayList<>();
            JSONArray hits = searchHits.getJSONArray("hits");
            for (int i = 0; i < hits.size(); i++) {
                T spuDocument = hits.getJSONObject(i).getObject("_source", type);
                documents.add(spuDocument);
            }
            pageResult.setTotalCount(total.intValue());
            pageResult.setDetails(documents);

            return pageResult;
        }
        return pageResult;
    }

    public <T> PageResult<T> parseDocumentSearchAfter(JSONObject jsonObject, Class<T> type) {
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotalCount(0);
        pageResult.setDetails(Collections.emptyList());
        if (jsonObject == null) {
            return pageResult;
        }
        JSONObject searchHits = jsonObject.getJSONObject("hits");
        if (searchHits == null) {
            return pageResult;
        }
        String searchAfter = null;
        Long total = searchHits.getJSONObject("total").getLong("value");
        if (total > 0) {
            List<T> documents = new ArrayList<>();
            JSONArray hits = searchHits.getJSONArray("hits");

            for (int i = 0; i < hits.size(); i++) {
                T spuDocument = hits.getJSONObject(i).getObject("_source", type);

                if (hits.size() - 1 == i) {

                    JSONArray arrsys = hits.getJSONObject(i).getJSONArray("sort");

                    searchAfter = getSort(arrsys);
                }
                documents.add(spuDocument);
            }
            pageResult.setTotalCount(total.intValue());
            pageResult.setDetails(documents);
            pageResult.setSearchAfter(searchAfter);
            return pageResult;
        }
        return pageResult;
    }


    private String getSort(JSONArray arrsys) {
        try {
            if (!arrsys.isEmpty()) {
                String searchAfter="";
                for(int i=0;i<arrsys.size();i++){
                    if(i==0){
                        searchAfter=searchAfter+ "\""+arrsys.get(i)+ "\"";
                    }else{
                        searchAfter=searchAfter+","+ "\""+arrsys.get(i)+ "\"";
                    }
                }
                return searchAfter;
            }
        } catch (Exception e) {
        }
        return null;
    }


}