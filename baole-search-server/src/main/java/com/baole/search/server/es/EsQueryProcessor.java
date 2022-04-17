package com.baole.search.server.es;

import com.baole.search.dto.es.enums.*;
import com.baole.search.dto.es.request.*;
import com.baole.search.server.context.EsContextDTO;
import com.google.common.collect.Maps;
import com.baole.common.constants.LoggerConstant;
import com.baole.common.elasticsearch.ElasticsearchCommand;
import com.baole.search.dto.es.enums.agg.EsDistinctCountEnum;
import com.baole.search.dto.es.response.PageResult;
import com.baole.common.error.ErrorCode;
import com.baole.common.utils.VerifyDataUtil;
import com.baole.search.dto.es.enums.agg.EsDistinctAggEnum;
import com.baole.search.dto.es.enums.agg.EsSumAggEnum;
import com.baole.search.dto.es.enums.index.EsBaseFieldEnum;
import com.baole.search.dto.es.response.EsAggregationResponseDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.ParsedTerms;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.CardinalityAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.ParsedCardinality;
import org.elasticsearch.search.aggregations.metrics.ParsedSum;
import org.elasticsearch.search.aggregations.metrics.SumAggregationBuilder;
import org.elasticsearch.search.aggregations.pipeline.BucketSortPipelineAggregationBuilder;
import org.elasticsearch.search.sort.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.SearchScrollHits;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SourceFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class EsQueryProcessor<C, T> {

    @Resource
    private EsQueryFactory<C, T> esQueryFactory;

    public long esCountQuery(EsRequestDTO esRequestDTO) {

        EsContextDTO<C, T> esContextDTO = new EsContextDTO<>();
        esContextDTO.setEsRequestDTO(esRequestDTO);

        EsQueryIndex<C, T> esQueryIndex = esQueryFactory.getByIndexCode(esRequestDTO.getIndexName());
        VerifyDataUtil.checkNotNull("esRequestDTO indexCode", ErrorCode.PARAM_ERROR, esQueryIndex);
        esContextDTO.setEsQueryIndex(esQueryIndex);

        NativeSearchQuery nativeSearchQuery = buildSearch(esContextDTO);
        esContextDTO.setNativeSearchQuery(nativeSearchQuery);

        return queryCount(esContextDTO);

    }
//
//    public PageResult<T> searchAfter(EsRequestDTO esRequestDTO) {
//
//        EsContextDTO<C, T> esContextDTO = new EsContextDTO<>();
//        esContextDTO.setEsRequestDTO(esRequestDTO);
//
//        EsQueryIndex<C, T> esQueryIndex = esQueryFactory.getByIndexCode(esRequestDTO.getIndexName());
//        VerifyDataUtil.checkNotNull("esRequestDTO indexCode", ErrorCode.PARAM_ERROR, esQueryIndex);
//        esContextDTO.setEsQueryIndex(esQueryIndex);
//
//        NativeSearchQuery nativeSearchQuery = buildSearch(esContextDTO);
//        esContextDTO.setNativeSearchQuery(nativeSearchQuery);
//
//        ElasticsearchCommand elasticsearchCommand = esQueryIndex.getElasticsearchCommand();
//        VerifyDataUtil.checkNotNull("esRequestDTO indexCode elasticsearchCommand", ErrorCode.PARAM_ERROR,
//                elasticsearchCommand);
//        PageResult<C> cPageResult = elasticsearchCommand
//                .searchAfter(esQueryIndex.getIndexName(), nativeSearchQuery, esQueryIndex.clazz(), esRequestDTO.getSearchAfter());
//        PageResult<T> result = new PageResult<>();
//        result.setSearchAfter(cPageResult.getSearchAfter());
//        result.setTotalCount(cPageResult.getTotalCount());
//        result.setPageNum(cPageResult.getPageNum());
//        result.setPageSize(cPageResult.getPageSize());
//        if (cPageResult != null && !CollectionUtils.isEmpty(cPageResult.getDetails())) {
//            List<T> list = esQueryIndex.convertResponse(cPageResult.getDetails(), esContextDTO);
//            result.setDetails(list);
//        }
//        return result;
//    }

    public EsResponseDTO<T> esQuery(EsRequestDTO esRequestDTO) {

        EsContextDTO<C, T> esContextDTO = new EsContextDTO<>();
        esContextDTO.setEsRequestDTO(esRequestDTO);

        EsQueryIndex<C, T> esQueryIndex = esQueryFactory.getByIndexCode(esRequestDTO.getIndexName());
        VerifyDataUtil.checkNotNull("esRequestDTO indexCode", ErrorCode.PARAM_ERROR, esQueryIndex);
        esContextDTO.setEsQueryIndex(esQueryIndex);

        NativeSearchQuery nativeSearchQuery = buildSearch(esContextDTO);
        esContextDTO.setNativeSearchQuery(nativeSearchQuery);

        SearchHits<C> searchHits = getSearchHits(esContextDTO);
        esContextDTO.setSearchHits(searchHits);

        EsResponseDTO<T> esResponseDTO = esQueryIndex.convertResponse(esContextDTO);
        esContextDTO.setEsResponseDTO(esResponseDTO);

        esResponseDTO.setTotalHits(searchHits.getTotalHits());
        if (esRequestDTO.getEsScrollRequestDTO() != null) {
            esResponseDTO.setScrollId(((SearchScrollHits<C>) searchHits).getScrollId());
        }

        injectAggregation(esContextDTO);

        return esResponseDTO;
    }



    private SearchHits<C> getSearchHits(EsContextDTO<C, T> esContextDTO) {

        EsRequestDTO esRequestDTO = esContextDTO.getEsRequestDTO();
        EsQueryIndex<C, T> esQueryIndex = esContextDTO.getEsQueryIndex();
        NativeSearchQuery nativeSearchQuery = esContextDTO.getNativeSearchQuery();

        ElasticsearchCommand elasticsearchCommand = esQueryIndex.getElasticsearchCommand();
        VerifyDataUtil.checkNotNull("esRequestDTO indexCode elasticsearchCommand", ErrorCode.PARAM_ERROR, elasticsearchCommand);

        EsScrollRequestDTO esScrollRequestDTO = esRequestDTO.getEsScrollRequestDTO();
        if (esScrollRequestDTO == null) {
            return elasticsearchCommand.query(esQueryIndex.getIndexName(), nativeSearchQuery, esQueryIndex.clazz());
        }

        return elasticsearchCommand.queryByScroll(esQueryIndex.getIndexName(), nativeSearchQuery, esQueryIndex.clazz(), esScrollRequestDTO.getScrollTimeInMillis(), esScrollRequestDTO.getScrollId());
    }

    private long queryCount(EsContextDTO<C, T> esContextDTO) {

        EsQueryIndex<C, T> esQueryIndex = esContextDTO.getEsQueryIndex();
        NativeSearchQuery nativeSearchQuery = esContextDTO.getNativeSearchQuery();

        ElasticsearchCommand elasticsearchCommand = esQueryIndex.getElasticsearchCommand();
        VerifyDataUtil.checkNotNull("esRequestDTO indexCode elasticsearchCommand", ErrorCode.PARAM_ERROR, elasticsearchCommand);
        return elasticsearchCommand.queryCount(esQueryIndex.getIndexName(), nativeSearchQuery, esQueryIndex.clazz());

    }

    private void injectAggregation(EsContextDTO<C, T> esContextDTO) {

        EsRequestDTO esRequestDTO = esContextDTO.getEsRequestDTO();
        SearchHits<C> searchHits = esContextDTO.getSearchHits();
        EsResponseDTO<T> esResponseDTO = esContextDTO.getEsResponseDTO();

        if (searchHits.getAggregations() == null) {
            return;
        }
        Aggregations aggregations = (Aggregations) searchHits.getAggregations().aggregations();

        //7.9.3版本
//        List<Aggregation> aggregationList = searchHits.getAggregations().asList();
        searchHits.getAggregations().aggregations();
        List<Aggregation> aggregationList=aggregations.asList();
        if (CollectionUtils.isEmpty(aggregationList)) {
            return;
        }

        List<EsAggregationRequestDTO> aggregationRequestDTOList = esRequestDTO.getAggregationRequestDTOList();
        Map<String, EsAggregationRequestDTO> aggregationRequestMap = aggregationRequestDTOList.stream()
                .collect(Collectors.toMap(EsAggregationRequestDTO::getName, esAggregationRequestDTO -> esAggregationRequestDTO, (a, b) -> b));

        List<EsAggregationResponseDTO> aggregationResponseList = new ArrayList<>();
        for (Aggregation aggregation : aggregationList) {

            String name = aggregation.getName();
            EsAggregationRequestDTO esAggregationRequestDTO = aggregationRequestMap.get(name);
            if (esAggregationRequestDTO == null) {
                continue;
            }

            EsAggregationResponseDTO esAggregationResponseDTO = new EsAggregationResponseDTO();
            esAggregationResponseDTO.setName(aggregation.getName());
            esAggregationResponseDTO.setAggregationType(esAggregationRequestDTO.getAggregationType());

            if (Objects.equals(esAggregationRequestDTO.getAggregationType(), EsAggregationEnum.DISTINCT.getAggregationType())) {

                ParsedTerms parsedStringTerms = (ParsedTerms) aggregation;

                esAggregationResponseDTO.setSumOtherCount(parsedStringTerms.getSumOfOtherDocCounts());

                List<Map<String, String>> fieldAndValueList = new ArrayList<>();
                parsedStringTerms.getBuckets().forEach(e -> {

                    Map<String, String> map = new HashMap<>();
                    map.put(EsDistinctAggEnum.KEY.getFieldName(), e.getKey().toString());
                    map.put(EsDistinctAggEnum.DOC_COUNT.getFieldName(), String.valueOf(e.getDocCount()));

                    fieldAndValueList.add(map);
                });

                esAggregationResponseDTO.setFieldAndValueList(fieldAndValueList);
            }

            if (Objects.equals(esAggregationRequestDTO.getAggregationType(), EsAggregationEnum.SUM.getAggregationType())) {

                ParsedSum parsedSum = (ParsedSum) aggregation;

                List<Map<String, String>> fieldAndValueList = new ArrayList<>();
                Map<String, String> map = new HashMap<>();
                map.put(EsSumAggEnum.SUM_VALUE.getFieldName(), Double.valueOf(parsedSum.getValue()).toString());

                fieldAndValueList.add(map);
                esAggregationResponseDTO.setFieldAndValueList(fieldAndValueList);
            }

            if (Objects.equals(esAggregationRequestDTO.getAggregationType(), EsAggregationEnum.DISTINCT_COUNT.getAggregationType())) {

                ParsedCardinality parsedCardinality = (ParsedCardinality) aggregation;

                List<Map<String, String>> fieldAndValueList = new ArrayList<>();
                Map<String, String> map = new HashMap<>();
                map.put(EsDistinctCountEnum.DISTINCT_COUNT.getFieldName(), parsedCardinality.getValue() + "");

                fieldAndValueList.add(map);
                esAggregationResponseDTO.setFieldAndValueList(fieldAndValueList);
            }

            aggregationResponseList.add(esAggregationResponseDTO);
        }

        esResponseDTO.setAggregationResponseList(aggregationResponseList);
    }

    private NativeSearchQuery buildSearch(EsContextDTO<C, T> esContextDTO) {

        EsRequestDTO esRequestDTO = esContextDTO.getEsRequestDTO();
        EsQueryIndex<C, T> esQueryIndex = esContextDTO.getEsQueryIndex();

        VerifyDataUtil.checkNumberNotExceedMinLimit("esRequestDTO page", ErrorCode.PARAM_ERROR, esRequestDTO.getEsPage(), 0);
        VerifyDataUtil.checkNumberNotExceedLimit("esRequestDTO size", ErrorCode.PARAM_ERROR, esRequestDTO.getSize(), 0, esQueryIndex.getMaxPageSize());

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();

        analyseSelectList(boolQueryBuilder, esContextDTO);

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder().withQuery(boolQueryBuilder);
        if (StringUtils.isBlank(esRequestDTO.getSearchAfter())) {
            nativeSearchQueryBuilder.withPageable(PageRequest.of(esRequestDTO.getEsPage(), esRequestDTO.getSize()));
        } else {
            nativeSearchQueryBuilder.withPageable(PageRequest.of(0, esRequestDTO.getSize()));
        }

        List<SortBuilder<?>> sortBuilderList = getSortBuilder(esContextDTO);
        if (!CollectionUtils.isEmpty(sortBuilderList)) {

            for (SortBuilder<?> sortBuilder : sortBuilderList) {

                if (sortBuilder != null) {
                    nativeSearchQueryBuilder.withSort(sortBuilder);
                }
            }
        }

        List<AbstractAggregationBuilder<?>> aggregationBuilderList = getAggregationList(esContextDTO);
        if (!CollectionUtils.isEmpty(aggregationBuilderList)) {
            aggregationBuilderList.forEach(nativeSearchQueryBuilder::addAggregation);
        }

        if (esRequestDTO.getSourceIncludes() != null) {

            SourceFilter sourceFilter = new FetchSourceFilter(esContextDTO.getEsRequestDTO().getSourceIncludes(), new String[]{});
            nativeSearchQueryBuilder.withSourceFilter(sourceFilter);
        }

        NativeSearchQuery nativeSearchQuery = nativeSearchQueryBuilder.build();
        nativeSearchQuery.setTrackTotalHits(true);

        return nativeSearchQuery;
    }

    private List<AbstractAggregationBuilder<?>> getAggregationList(EsContextDTO<C, T> esContextDTO) {

        EsRequestDTO esRequestDTO = esContextDTO.getEsRequestDTO();

        List<EsAggregationRequestDTO> aggregationRequestDTOList = esRequestDTO.getAggregationRequestDTOList();
        if (CollectionUtils.isEmpty(aggregationRequestDTOList)) {
            return null;
        }

        List<AbstractAggregationBuilder<?>> list = new ArrayList<>();
        for (EsAggregationRequestDTO esAggregationRequestDTO : aggregationRequestDTOList) {

            AbstractAggregationBuilder<?> aggregationBuilder = getAggregation(esContextDTO, esAggregationRequestDTO);
            if (aggregationBuilder == null) {
                continue;
            }

            list.add(aggregationBuilder);
        }

        return list;
    }

    private AbstractAggregationBuilder<?> getAggregation(EsContextDTO<C, T> esContextDTO, EsAggregationRequestDTO esAggregationRequestDTO) {

        VerifyDataUtil.checkNotNull("esAggregationRequestDTO", ErrorCode.PARAM_ERROR, esAggregationRequestDTO);
        VerifyDataUtil.checkNotNull("esAggregationRequestDTO name", ErrorCode.PARAM_ERROR, esAggregationRequestDTO.getName());
        VerifyDataUtil.checkNotNull("esAggregationRequestDTO fieldName", ErrorCode.PARAM_ERROR, esAggregationRequestDTO.getFieldName());

        if (Objects.equals(esAggregationRequestDTO.getAggregationType(), EsAggregationEnum.DISTINCT.getAggregationType())) {

            VerifyDataUtil.checkNumberNotExceedMinLimit("esAggregationRequestDTO page", ErrorCode.PARAM_ERROR, esAggregationRequestDTO.getFrom(), 0);
            VerifyDataUtil.checkNumberNotExceedMinLimit("esAggregationRequestDTO size", ErrorCode.PARAM_ERROR, esAggregationRequestDTO.getSize(), 0);
            VerifyDataUtil.checkNumberNotExceedMinLimit("esAggregationRequestDTO selectTotalCount", ErrorCode.PARAM_ERROR, esAggregationRequestDTO.getSelectTotalCount(), 0);

            TermsAggregationBuilder termsAggregationBuilder = AggregationBuilders.terms(esAggregationRequestDTO.getName());
            String fieldName = getFieldName(esContextDTO, esAggregationRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("esAggregationRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);

            termsAggregationBuilder.field(fieldName).size(esAggregationRequestDTO.getSelectTotalCount());
            termsAggregationBuilder.subAggregation(
                    new BucketSortPipelineAggregationBuilder("bucket", null)
                            .from(esAggregationRequestDTO.getFrom())
                            .size(esAggregationRequestDTO.getSize()));

            return termsAggregationBuilder;
        }

        if (Objects.equals(esAggregationRequestDTO.getAggregationType(), EsAggregationEnum.SUM.getAggregationType())) {

            SumAggregationBuilder sumAggregationBuilder = AggregationBuilders.sum(esAggregationRequestDTO.getName());
            String fieldName = getFieldName(esContextDTO, esAggregationRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("esAggregationRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);

            sumAggregationBuilder.field(fieldName);

            return sumAggregationBuilder;
        }

        if (Objects.equals(esAggregationRequestDTO.getAggregationType(), EsAggregationEnum.DISTINCT_COUNT.getAggregationType())) {

            CardinalityAggregationBuilder cardinalityAggregationBuilder = AggregationBuilders.cardinality(esAggregationRequestDTO.getName());
            String fieldName = getFieldName(esContextDTO,esAggregationRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("esAggregationRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);

            cardinalityAggregationBuilder.field(fieldName);

            return cardinalityAggregationBuilder;
        }

        return null;
    }

    private void analyseSelectList(BoolQueryBuilder boolQueryBuilder, EsContextDTO<C, T> esContextDTO) {

        EsRequestDTO esRequestDTO = esContextDTO.getEsRequestDTO();

        if (CollectionUtils.isEmpty(esRequestDTO.getSelectForAndList())) {
            return;
        }

        for (EsEntityRequestDTO esEntityRequestDTO : esRequestDTO.getSelectForAndList()) {

            QueryBuilder queryBuilder = getQueryBuilderByQueryType(esEntityRequestDTO, esContextDTO);
            VerifyDataUtil.checkNotNull("esRequestDTO selectForAndList queryType", ErrorCode.PARAM_ERROR, queryBuilder);

            boolQueryBuilder.filter(queryBuilder);
        }
    }

    private QueryBuilder getQueryBuilderByQueryType(EsEntityRequestDTO esEntityRequestDTO, EsContextDTO<C, T> esContextDTO) {

        VerifyDataUtil.checkNotNull("esEntityRequestDTO", ErrorCode.PARAM_ERROR, esEntityRequestDTO);

        if (Objects.equals(EsQueryEnum.TERM.getQueryType(), esEntityRequestDTO.getQueryType())) {

            String fieldName = getFieldName(esContextDTO, esEntityRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("term esEntityRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);
            VerifyDataUtil.checkListNotEmpty("term esEntityRequestDTO values", ErrorCode.PARAM_ERROR, esEntityRequestDTO.getValues());

            return QueryBuilders.termQuery(fieldName, esEntityRequestDTO.getValues().get(0));
        }

        if (Objects.equals(EsQueryEnum.TERMS.getQueryType(), esEntityRequestDTO.getQueryType())) {

            String fieldName = getFieldName(esContextDTO, esEntityRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("terms esEntityRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);
            VerifyDataUtil.checkListNotEmpty("terms esEntityRequestDTO values", ErrorCode.PARAM_ERROR, esEntityRequestDTO.getValues());

            return QueryBuilders.termsQuery(fieldName, esEntityRequestDTO.getValues());
        }

        if (Objects.equals(EsQueryEnum.EXIST.getQueryType(), esEntityRequestDTO.getQueryType())) {

            String fieldName = getFieldName(esContextDTO,  esEntityRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("exist esEntityRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);

            return QueryBuilders.existsQuery(fieldName);
        }

        if (Objects.equals(EsQueryEnum.NESTED.getQueryType(), esEntityRequestDTO.getQueryType())) {

            VerifyDataUtil.checkNotNull("nested esEntityRequestDTO nestedPath", ErrorCode.PARAM_ERROR, esEntityRequestDTO.getNestedPath());

            return QueryBuilders.nestedQuery(esEntityRequestDTO.getNestedPath(), getInnerQuery(esEntityRequestDTO, esContextDTO), ScoreMode.Avg);
        }

        if (Objects.equals(EsQueryEnum.RANGE.getQueryType(), esEntityRequestDTO.getQueryType())) {

            return getRangeQuery(esEntityRequestDTO, esContextDTO);
        }

        if (Objects.equals(EsQueryEnum.MATCH_PHRASE.getQueryType(), esEntityRequestDTO.getQueryType())) {

            String fieldName = getFieldName(esContextDTO,  esEntityRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("match_phrase esEntityRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);
            VerifyDataUtil.checkListNotEmpty("match_phrase esEntityRequestDTO values", ErrorCode.PARAM_ERROR, esEntityRequestDTO.getValues());

            return QueryBuilders.matchPhraseQuery(fieldName, esEntityRequestDTO.getValues().get(0).toString());
        }

        if (Objects.equals(EsQueryEnum.TERMS_PRECISE.getQueryType(), esEntityRequestDTO.getQueryType())) {

            String fieldName = getFieldName(esContextDTO, esEntityRequestDTO.getFieldName());
            VerifyDataUtil.checkNotNull("terms_precise esEntityRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);
            VerifyDataUtil.checkListNotEmpty("terms_precise esEntityRequestDTO values", ErrorCode.PARAM_ERROR, esEntityRequestDTO.getValues());

            return getTermsPrecise(fieldName, esEntityRequestDTO.getValues());
        }

        if (Objects.equals(EsQueryEnum.INNER.getQueryType(), esEntityRequestDTO.getQueryType())) {

            return getInnerQuery(esEntityRequestDTO, esContextDTO);
        }

        return null;
    }

    private QueryBuilder getTermsPrecise(String name, List<Object> values) {

        VerifyDataUtil.checkListNotEmpty("terms_precise esEntityRequestDTO values", ErrorCode.PARAM_ERROR, values);

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (Object o : values) {

            boolQueryBuilder.filter(QueryBuilders.termsQuery(name, o));
        }

        int size = values.size();

        Map<String, Object> params = new HashMap<>();
        params.put("size", size);
        Script script = new Script(ScriptType.INLINE, Script.DEFAULT_SCRIPT_LANG, "doc['" + name + "'].length == params.size", params);

        ScriptQueryBuilder scriptQueryBuilder = QueryBuilders.scriptQuery(script);
        boolQueryBuilder.filter(scriptQueryBuilder);

        return boolQueryBuilder;
    }

    private QueryBuilder getRangeQuery(EsEntityRequestDTO esEntityRequestDTO, EsContextDTO<C, T> esContextDTO) {

        EsRangeRequestDTO esRangeRequestDTO = esEntityRequestDTO.getEsRangeRequestDTO();
        VerifyDataUtil.checkNotNull("range esEntityRequestDTO esRangeRequestDTO", ErrorCode.PARAM_ERROR, esRangeRequestDTO);

        String fieldName = getFieldName(esContextDTO, esEntityRequestDTO.getFieldName());
        VerifyDataUtil.checkNotNull("range esEntityRequestDTO fieldName", ErrorCode.PARAM_ERROR, fieldName);

        RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(fieldName);

        if (Objects.equals(esRangeRequestDTO.getFromType(), EsRangeEnum.NORMAL.getRangeType())) {
            rangeQueryBuilder.from(esRangeRequestDTO.getFrom(), false);
        }

        if (Objects.equals(esRangeRequestDTO.getFromType(), EsRangeEnum.INCLUDE.getRangeType())) {
            rangeQueryBuilder.from(esRangeRequestDTO.getFrom(), true);
        }

        if (Objects.equals(esRangeRequestDTO.getToType(), EsRangeEnum.NORMAL.getRangeType())) {
            rangeQueryBuilder.to(esRangeRequestDTO.getTo(), false);
        }

        if (Objects.equals(esRangeRequestDTO.getToType(), EsRangeEnum.INCLUDE.getRangeType())) {
            rangeQueryBuilder.to(esRangeRequestDTO.getTo(), true);
        }

        return rangeQueryBuilder;
    }

    private QueryBuilder getInnerQuery(EsEntityRequestDTO esEntityRequestDTO, EsContextDTO<C, T> esContextDTO) {

        VerifyDataUtil.checkListNotEmpty("inner esEntityRequestDTO innerRequestList", ErrorCode.PARAM_ERROR, esEntityRequestDTO.getInnerRequestList());

        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (EsEntityRequestDTO inner : esEntityRequestDTO.getInnerRequestList()) {

            QueryBuilder queryBuilder = getQueryBuilderByQueryType(inner, esContextDTO);
            VerifyDataUtil.checkNotNull("inner esEntityRequestDTO innerRequestList queryType", ErrorCode.PARAM_ERROR, queryBuilder);

            if (Objects.equals(esEntityRequestDTO.getInnerRequestType(), EsLinkEnum.AND.getLinkType())) {
                boolQueryBuilder.filter(queryBuilder);
                continue;
            }

            if (Objects.equals(esEntityRequestDTO.getInnerRequestType(), EsLinkEnum.OR.getLinkType())) {
                boolQueryBuilder.should(queryBuilder);
                continue;
            }

            if (Objects.equals(esEntityRequestDTO.getInnerRequestType(), EsLinkEnum.NOT.getLinkType())) {
                boolQueryBuilder.mustNot(queryBuilder);
            }
        }

        return boolQueryBuilder;
    }

    private String getFieldName(EsContextDTO<C, T> esContextDTO, String fieldName) {


        EsQueryIndex<C, T> esQueryIndex = esContextDTO.getEsQueryIndex();
        VerifyDataUtil.checkBooleanTrue("fieldName " + (fieldName == null ? "" : fieldName),
                ErrorCode.PARAM_ERROR, esQueryIndex.checkField(fieldName));

        return fieldName;

    }

    private List<SortBuilder<?>> getSortBuilder(EsContextDTO<C, T> esContextDTO) {

        EsRequestDTO esRequestDTO = esContextDTO.getEsRequestDTO();

        List<EsSortRequestDTO> sortRequestDTOList = esRequestDTO.getSortRequestDTOList();
        if (CollectionUtils.isEmpty(sortRequestDTOList)) {
            return null;
        }

        List<SortBuilder<?>> list = new ArrayList<>();
        for (EsSortRequestDTO esSortRequestDTO : sortRequestDTOList) {

            if (esSortRequestDTO == null) {
                continue;
            }

            String fieldName = getFieldName(esContextDTO, esSortRequestDTO.getSortFieldName());
            VerifyDataUtil.checkNotNull("esSortRequestDTO sortFieldName", ErrorCode.PARAM_ERROR, fieldName);

            SortBuilder<FieldSortBuilder> sortBuilder = SortBuilders.fieldSort(fieldName);

            SortOrder sortOrder = null;
            if (Objects.equals(esSortRequestDTO.getSortType(), EsSortEnum.ASC.getSortType())) {
                sortOrder = SortOrder.ASC;
            } else if (Objects.equals(esSortRequestDTO.getSortType(), EsSortEnum.DESC.getSortType())) {
                sortOrder = SortOrder.DESC;
            }

            if (sortOrder == null) {
                continue;
            }

            FieldSortBuilder fieldSortBuilder = sortBuilder.order(sortOrder);
            if (!StringUtils.isBlank(esSortRequestDTO.getNestedSortPath())
                    && esSortRequestDTO.getEsNestedSortFilterRequestDTO() != null) {
                String filterFieldName = getFieldName(esContextDTO,
                        esSortRequestDTO.getEsNestedSortFilterRequestDTO().getFieldName());
                VerifyDataUtil.checkNotNull("esSortRequestDTO filterFieldName", ErrorCode.PARAM_ERROR, filterFieldName);
                VerifyDataUtil.checkNotNull("esSortRequestDTO filterValue", ErrorCode.PARAM_ERROR,
                        esSortRequestDTO.getEsNestedSortFilterRequestDTO().getValue());

                NestedSortBuilder nestedSortBuilder = new NestedSortBuilder(esSortRequestDTO.getNestedSortPath());
                TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(filterFieldName,
                        esSortRequestDTO.getEsNestedSortFilterRequestDTO().getValue());
                nestedSortBuilder.setFilter(termQueryBuilder);
                fieldSortBuilder = fieldSortBuilder.setNestedSort(nestedSortBuilder);
            }
            list.add(fieldSortBuilder);
        }

        return list;
    }


}