package com.baole.search.server.es.index;

import com.baole.common.constants.LoggerConstant;
import com.baole.common.elasticsearch.ElasticsearchCommand;
import com.baole.search.domain.bo.es.EsGoodsInfoBO;
import com.baole.search.dto.es.enums.index.EsGoodsFieldEnum;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import com.baole.search.server.context.EsContextDTO;
import com.baole.search.server.es.EsQueryIndex;
import com.baole.search.server.convert.EsQueryApiConvert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component("EsQueryForGoodsIndex")
@Slf4j(topic = LoggerConstant.LoggerTopic.SERVICE_LOGGER)
public class EsQueryForGoodsIndex implements EsQueryIndex<EsGoodsInfoBO, EsGoodsResponseDTO> {

    @Resource
    private ElasticsearchCommand elasticsearchCommandByGoods;

    @Override
    public String getIndexName() {

        return getEsQueryIndexName("saas-mp-goods-info");
    }

    @Override
    public String getExtMappingIndexName() {
        return "saas-mp-goods-info";
    }

    @Override
    public ElasticsearchCommand getElasticsearchCommand() {

        return elasticsearchCommandByGoods;
    }

    @Override
    public Class<EsGoodsInfoBO> clazz() {

        return EsGoodsInfoBO.class;
    }

    @Override
    public EsResponseDTO<EsGoodsResponseDTO> convertResponse(EsContextDTO<EsGoodsInfoBO, EsGoodsResponseDTO> esContextDTO) {

        SearchHits<EsGoodsInfoBO> searchHits = esContextDTO.getSearchHits();
        List<EsGoodsInfoBO> list = searchHits.get().map(SearchHit::getContent).collect(Collectors.toList());

        EsResponseDTO<EsGoodsResponseDTO> esResponseDTO = new EsResponseDTO<>();
        List<EsGoodsResponseDTO> responseList = new ArrayList<>();


        for (EsGoodsInfoBO esGoodsInfoBO : list) {

            EsGoodsResponseDTO esGoodsResponseDTO = EsQueryApiConvert.INSTANCE.convertDTO(esGoodsInfoBO);
            responseList.add(esGoodsResponseDTO);

        }

        esResponseDTO.setResponseList(responseList);

        return esResponseDTO;
    }

    @Override
    public boolean checkField(String fieldName) {

        return EsGoodsFieldEnum.checkFieldName(fieldName);
    }

    @Override
    public List<EsGoodsResponseDTO> convertResponse(List<EsGoodsInfoBO> list,EsContextDTO<EsGoodsInfoBO,
            EsGoodsResponseDTO> esContextDTO) {

        List<EsGoodsResponseDTO> responseList = new ArrayList<>();

        for (EsGoodsInfoBO esGoodsInfoBO : list) {

            EsGoodsResponseDTO esGoodsResponseDTO = EsQueryApiConvert.INSTANCE.convertDTO(esGoodsInfoBO);
            responseList.add(esGoodsResponseDTO);

        }
        return responseList;
    }
}
