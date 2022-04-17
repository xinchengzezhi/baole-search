package com.baole.search.boot;

import com.baole.common.dto.SoaRespCommonErrorDTO;
import com.baole.search.api.EsQueryApi;
import com.baole.search.dto.es.enums.EsAggregationEnum;
import com.baole.search.dto.es.enums.EsIndexEnum;
import com.baole.search.dto.es.enums.EsQueryEnum;
import com.baole.search.dto.es.enums.agg.EsDistinctAggEnum;
import com.baole.search.dto.es.enums.agg.EsDistinctCountEnum;
import com.baole.search.dto.es.enums.index.EsGoodsFieldEnum;
import com.baole.search.dto.es.request.EsAggregationRequestDTO;
import com.baole.search.dto.es.request.EsEntityRequestDTO;
import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.response.EsAggregationResponseDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import com.weimob.soa.common.response.SoaResponse;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppMainTest {

    @Resource
    private EsQueryApi esQueryApi;

    @Test
    void main() {

        EsRequestDTO esRequestDTO = new EsRequestDTO();

        esRequestDTO.setTcode("weimob");
        esRequestDTO.setSourceProductId(145L);
        esRequestDTO.setIndexCode(EsIndexEnum.MP_GOODS_INFO_INDEX.getIndexCode());
        esRequestDTO.setPage(1);
        esRequestDTO.setSize(1);

        List<EsAggregationRequestDTO> aggregationRequestDTOList = new ArrayList<>();
        EsAggregationRequestDTO esAggregationRequestDTO = new EsAggregationRequestDTO();
        esAggregationRequestDTO.setName("distinct_count");
        // 聚合的字段
        esAggregationRequestDTO.setFieldName(EsGoodsFieldEnum.V_ID.getFieldName());
        esAggregationRequestDTO.setAggregationType(EsAggregationEnum.DISTINCT_COUNT.getAggregationType());

        aggregationRequestDTOList.add(esAggregationRequestDTO);

        esRequestDTO.setAggregationRequestDTOList(aggregationRequestDTOList);

        SoaResponse<EsResponseDTO<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> soaResponse = esQueryApi.esQueryByGoods(esRequestDTO);
        EsResponseDTO<EsGoodsResponseDTO> esResponseDTO = soaResponse.getResponseVo();

        EsAggregationResponseDTO esAggregationResponseDTO = esResponseDTO.getAggregationResponseList().get(0);
        List<Map<String, String>> fieldAndValueList = esAggregationResponseDTO.getFieldAndValueList();
        for (Map<String, String> map : fieldAndValueList) {

            // 总数
            System.out.println("distinctCount: " + map.get(EsDistinctCountEnum.DISTINCT_COUNT.getFieldName()));

            System.out.println("----");
        }
    }
}