package com.baole.search.api;

import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.request.EsSearchRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.PageResult;
import com.baole.search.dto.es.response.SoaRespCommonErrorDTO;
import com.baole.search.dto.es.response.SoaResponse;
import com.baole.search.dto.es.response.entity.*;

/**
 * es 检索
 */
public interface EsQueryApi {

    SoaResponse<EsResponseDTO<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> esQueryByGoods(EsRequestDTO esRequestDTO);

    SoaResponse<EsResponseDTO<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> esQueryByGoodsV1(EsSearchRequestDTO requestDTO);

    SoaResponse<Long, SoaRespCommonErrorDTO> esQueryCountByGoods(
            EsRequestDTO esRequestDTO);



    SoaResponse<EsResponseDTO<EsItemSkuResponseDTO>, SoaRespCommonErrorDTO> esQueryByItemSku(EsRequestDTO esRequestDTO);

    SoaResponse<EsResponseDTO<EsItemResponseDTO>, SoaRespCommonErrorDTO> esQueryByItem(EsRequestDTO esRequestDTO);

    SoaResponse<PageResult<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> esSearchAfterByGoods(
            EsRequestDTO esRequestDTO);

}
