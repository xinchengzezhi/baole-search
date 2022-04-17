package com.baole.search.server.es;

import com.alibaba.fastjson.JSON;
import com.baole.common.elasticsearch.ElasticsearchCommand;
import com.baole.common.error.ErrorCode;
import com.baole.common.utils.VerifyDataUtil;
import com.baole.search.domain.bo.es.EsExtBO;
import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.server.context.EsContextDTO;
import org.springframework.util.CollectionUtils;

import java.util.*;

public interface EsQueryIndex<C, T> {

    String getIndexName();

    String getExtMappingIndexName();

    default String getEsQueryIndexName(String indexKey) {

        VerifyDataUtil.checkNotBlank("indexKey", ErrorCode.ES_INDEX_NAME_CANNOT_BLANK, indexKey);

        String indexName = EsQueryIndexName.esQueryIndexNameMap.get(indexKey);
        VerifyDataUtil.checkNotNull("indexValue", ErrorCode.ES_INDEX_NAME_CANNOT_BLANK, indexName);

        return indexName;
    }

    ElasticsearchCommand getElasticsearchCommand();

    Class<C> clazz();

    EsResponseDTO<T> convertResponse(EsContextDTO<C, T> esContextDTO);

    boolean checkField(String fieldName);

    default int getMaxPageSize() {

        return 5000;
    }

    List<T> convertResponse(List<C> list, EsContextDTO<C,
            T> esContextDTO);

}
