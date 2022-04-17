package com.baole.search.server.es.index;

import com.baole.common.constants.LoggerConstant;
import com.baole.common.elasticsearch.ElasticsearchCommand;
import com.baole.search.domain.bo.es.EsItemBO;
import com.baole.search.dto.es.enums.index.EsItemFieldEnum;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.entity.EsItemResponseDTO;
import com.baole.search.server.context.EsContextDTO;
import com.baole.search.server.convert.EsQueryApiConvert;
import com.baole.search.server.es.EsQueryIndex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("EsQueryForItemIndex")
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class EsQueryForItemIndex implements EsQueryIndex<EsItemBO, EsItemResponseDTO> {

    @Resource
    private ElasticsearchCommand elasticsearchCommandByGoods;

    @Override
    public String getIndexName() {

        return getEsQueryIndexName("saas-mp-goods-item");
    }

    @Override
    public String getExtMappingIndexName() {
        return "saas-mp-goods-item";
    }

    @Override
    public ElasticsearchCommand getElasticsearchCommand() {

        return elasticsearchCommandByGoods;
    }

    @Override
    public Class<EsItemBO> clazz() {

        return EsItemBO.class;
    }

    @Override
    public EsResponseDTO<EsItemResponseDTO> convertResponse(EsContextDTO<EsItemBO, EsItemResponseDTO> esContextDTO) {

        SearchHits<EsItemBO> searchHits = esContextDTO.getSearchHits();
        List<EsItemBO> list = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());

        EsResponseDTO<EsItemResponseDTO> esResponseDTO = new EsResponseDTO<>();
        List<EsItemResponseDTO> responseList = new ArrayList<>();


        for (EsItemBO esGoodsInfoBO : list) {

            EsItemResponseDTO esGoodsResponseDTO = EsQueryApiConvert.INSTANCE.convertDTO(esGoodsInfoBO);
            responseList.add(esGoodsResponseDTO);

        }

        esResponseDTO.setResponseList(responseList);

        return esResponseDTO;
    }

    @Override
    public boolean checkField(String fieldName) {

        return EsItemFieldEnum.checkFieldName(fieldName);
    }

    @Override
    public List<EsItemResponseDTO> convertResponse(List<EsItemBO> list,
            EsContextDTO<EsItemBO, EsItemResponseDTO> esContextDTO) {
        return null;
    }
}
