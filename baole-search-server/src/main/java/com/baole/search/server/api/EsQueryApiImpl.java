package com.baole.search.server.api;

import com.alibaba.dubbo.config.annotation.Service;
import com.baole.search.api.EsQueryApi;
import com.baole.common.constants.LoggerConstant;
import com.baole.search.domain.bo.es.*;
import com.baole.search.dto.es.request.EsSearchRequestDTO;
import com.baole.search.dto.es.response.PageResult;
import com.baole.common.error.ErrorCode;
import com.baole.common.utils.VerifyDataUtil;
import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.SoaRespCommonErrorDTO;
import com.baole.search.dto.es.response.SoaResponse;
import com.baole.search.dto.es.response.entity.*;
import com.baole.search.server.SoaResponseProcessor;
import com.baole.search.server.es.BBossQueryProcessor;
import com.baole.search.server.es.EsQueryProcessor;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

@Service(timeout = 3000)
@Slf4j(topic = LoggerConstant.LoggerTopic.API_LOGGER)
public class EsQueryApiImpl extends SoaResponseProcessor implements EsQueryApi {

    @Resource
    private EsQueryProcessor<EsGoodsInfoBO, EsGoodsResponseDTO> esQueryProcessor;

    @Resource
    private BBossQueryProcessor<EsGoodsInfoBO, EsGoodsResponseDTO> bossQueryProcessor;

    @Resource
    private EsQueryProcessor<EsItemSkuBO, EsItemSkuResponseDTO> esItemSkuQueryProcessor;

    @Resource
    private EsQueryProcessor<EsItemBO, EsItemResponseDTO> esItemQueryProcessor;


    @Override
    public SoaResponse<EsResponseDTO<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> esQueryByGoods(
            EsRequestDTO esRequestDTO) {

        return process(() -> {
            VerifyDataUtil.checkNotNull("esRequestDTO", ErrorCode.PARAM_ERROR, esRequestDTO);
            return esQueryProcessor.esQuery(esRequestDTO);
        });
    }

    @Override
    public SoaResponse<EsResponseDTO<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> esQueryByGoodsV1(
            EsSearchRequestDTO requestDTO) {
        return process(() -> {

            EsResponseDTO<EsGoodsResponseDTO> esResponseDTO = bossQueryProcessor.esQueryByGoodsV1(requestDTO);

            return esResponseDTO;
        });
    }

    @Override
    public SoaResponse<Long, SoaRespCommonErrorDTO> esQueryCountByGoods(
            EsRequestDTO esRequestDTO) {

        return process(() -> {

            VerifyDataUtil.checkNotNull("esRequestDTO", ErrorCode.PARAM_ERROR, esRequestDTO);
            long count = esQueryProcessor.esCountQuery(esRequestDTO);

            return count;
        });
    }


    @Override
    public SoaResponse<EsResponseDTO<EsItemSkuResponseDTO>, SoaRespCommonErrorDTO> esQueryByItemSku(
            EsRequestDTO esRequestDTO) {
        return process(() -> {

            VerifyDataUtil.checkNotNull("esRequestDTO", ErrorCode.PARAM_ERROR, esRequestDTO);
            EsResponseDTO<EsItemSkuResponseDTO> esResponseDTO = esItemSkuQueryProcessor.esQuery(esRequestDTO);
            VerifyDataUtil.checkNotNull("esResponseDTO", ErrorCode.PARAM_ERROR, esResponseDTO);
            return esResponseDTO;
        });
    }

    @Override
    public SoaResponse<EsResponseDTO<EsItemResponseDTO>, SoaRespCommonErrorDTO> esQueryByItem(
            EsRequestDTO esRequestDTO) {
        return process(() -> {

            VerifyDataUtil.checkNotNull("esRequestDTO", ErrorCode.PARAM_ERROR, esRequestDTO);
            EsResponseDTO<EsItemResponseDTO> esResponseDTO = esItemQueryProcessor.esQuery(esRequestDTO);
            VerifyDataUtil.checkNotNull("esResponseDTO", ErrorCode.PARAM_ERROR, esResponseDTO);
            return esResponseDTO;
        });
    }

    @Override
    public SoaResponse<PageResult<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> esSearchAfterByGoods(EsRequestDTO esRequestDTO) {
        return null;
    }
}
