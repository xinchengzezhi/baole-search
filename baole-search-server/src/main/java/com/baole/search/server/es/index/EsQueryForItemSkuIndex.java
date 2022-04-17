package com.baole.search.server.es.index;

import com.baole.common.constants.LoggerConstant;
import com.baole.common.elasticsearch.ElasticsearchCommand;
import com.baole.search.domain.bo.es.EsItemSkuBO;
import com.baole.search.dto.es.enums.index.EsItemSkuFieldEnum;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.entity.EsItemSkuResponseDTO;
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

@Component("EsQueryForItemSkuIndex")
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class EsQueryForItemSkuIndex implements EsQueryIndex<EsItemSkuBO, EsItemSkuResponseDTO> {

    @Resource
    private ElasticsearchCommand elasticsearchCommandByGoods;

    @Override
    public String getIndexName() {

        return getEsQueryIndexName("saas-mp-goods-item-sku");
    }

    @Override
    public String getExtMappingIndexName() {
        return "saas-mp-goods-item-sku";
    }

    @Override
    public ElasticsearchCommand getElasticsearchCommand() {

        return elasticsearchCommandByGoods;
    }

    @Override
    public Class<EsItemSkuBO> clazz() {

        return EsItemSkuBO.class;
    }

    @Override
    public EsResponseDTO<EsItemSkuResponseDTO> convertResponse(EsContextDTO<EsItemSkuBO, EsItemSkuResponseDTO> esContextDTO) {

        SearchHits<EsItemSkuBO> searchHits = esContextDTO.getSearchHits();
        List<EsItemSkuBO> list = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());

        EsResponseDTO<EsItemSkuResponseDTO> esResponseDTO = new EsResponseDTO<>();
        List<EsItemSkuResponseDTO> responseList = new ArrayList<>();


        for (EsItemSkuBO esGoodsInfoBO : list) {

            EsItemSkuResponseDTO esGoodsResponseDTO = EsQueryApiConvert.INSTANCE.convertDTO(esGoodsInfoBO);
            responseList.add(esGoodsResponseDTO);

        }

        esResponseDTO.setResponseList(responseList);

        return esResponseDTO;
    }

    @Override
    public boolean checkField(String fieldName) {

        return EsItemSkuFieldEnum.checkFieldName(fieldName);
    }

    @Override
    public List<EsItemSkuResponseDTO> convertResponse(List<EsItemSkuBO> list,
            EsContextDTO<EsItemSkuBO, EsItemSkuResponseDTO> esContextDTO) {
        return null;
    }
}
