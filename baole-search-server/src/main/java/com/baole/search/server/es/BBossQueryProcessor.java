package com.baole.search.server.es;

import com.baole.search.dto.es.request.EsSearchRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import com.google.common.collect.Maps;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.frameworkset.elasticsearch.entity.ESDatas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
@Component
public class BBossQueryProcessor<C, T> {

    @Autowired
    private BBossESStarter bbossESStarter;

    public EsResponseDTO<EsGoodsResponseDTO> esQueryByGoodsV1(EsSearchRequestDTO requestDTO) {
        ClientInterface client = bbossESStarter.getConfigRestClient("esmapper/goods.xml");
        Map<String, Object> params = new HashMap<>();
        params.put("includes", requestDTO.getIncludes());
        params.put("trackTotalHits", requestDTO.getTrackTotalHits());
        params.put("tcode", requestDTO.getTcode());
        params.put("sourceProductId", requestDTO.getSourceProductId());
        params.put("isDeleted", requestDTO.isDelete());
        params.put("sourceProductInstanceId", requestDTO.getSourceProductInstanceId());
        params.put("merchantId", requestDTO.getMerchantId());
        params.put("bosId", requestDTO.getBosId());
        params.put("vid", requestDTO.getVid());
        params.put("isCanSell", requestDTO.getIsCanSell());
        params.put("isOnline", requestDTO.getIsOnline());
        params.put("channelIds", requestDTO.getChannelIds());
        params.put("isAssigned", requestDTO.getIsAssigned());
        params.put("tagCodeList", requestDTO.getTagCodeList());
        params.put("from", requestDTO.getFrom());
        params.put("size", requestDTO.getSize());

        Map<String, Object> sortColumn = Maps.newHashMap();
        sortColumn.put(requestDTO.getSortColumn(), requestDTO.getSort());
        params.put("sortColumn", sortColumn);

        String indexName = "saas-mp-goods-info/_search";
        String templateName = "queryGoodsInfo";
        ESDatas<EsGoodsResponseDTO> responseDTOESDatas = client.searchList(indexName, templateName, params, EsGoodsResponseDTO.class);
        EsResponseDTO<EsGoodsResponseDTO> esResponseDTO = new EsResponseDTO<>();
        esResponseDTO.setResponseList(responseDTOESDatas.getDatas());
        esResponseDTO.setTotalHits(responseDTOESDatas.getTotalSize());
        return esResponseDTO;
    }
}
