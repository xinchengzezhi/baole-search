//package com.baole.search.boot;
//
//import com.alibaba.fastjson.JSON;
//import com.baole.common.dto.SoaRespCommonErrorDTO;
//import com.baole.search.api.EsQueryApi;
//import com.baole.search.dto.es.enums.EsIndexEnum;
//import com.baole.search.dto.es.request.EsEntityRequestDTO;
//import com.baole.search.dto.es.request.EsRequestDTO;
//import com.baole.search.dto.es.response.EsResponseDTO;
//import com.baole.search.dto.es.response.entity.EsItemResponseDTO;
//import com.baole.search.dto.es.response.entity.EsItemSkuResponseDTO;
//import com.baole.search.dto.es.response.entity.EsSkuSpecResponseDTO;
//import com.weimob.soa.common.response.SoaResponse;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//class ItemTest {
//
//    @Resource
//    private EsQueryApi esQueryApi;
//
//    @Test
//    void main() {
//
//        EsRequestDTO esRequestDTO = new EsRequestDTO();
//        esRequestDTO.setTcode("tcode");
//        esRequestDTO.setSourceProductId(200001110L);
//        esRequestDTO.setIndexCode(EsIndexEnum.MP_GOODS_INFO_INDEX.getIndexCode());
//        esRequestDTO.setDelete(true);
//        esRequestDTO.setSupplementaryExtInfo(true);
//
//        List<EsEntityRequestDTO> selectForAndList = new ArrayList<>();
//        esRequestDTO.setSelectForAndList(selectForAndList);
//
////        EsEntityRequestDTO esEntityRequestDTO = new EsEntityRequestDTO();
////        esEntityRequestDTO.setFieldName("goodsId");
////        esEntityRequestDTO.setValues(Collections.singletonList(1264348481112L));
////        esEntityRequestDTO.setExtField(true);
////        selectForAndList.add(esEntityRequestDTO);
//        SoaResponse<Long, SoaRespCommonErrorDTO> soaResponse = esQueryApi
//                .esQueryCountByGoods(esRequestDTO);
//        Long count = soaResponse.getResponseVo();
//
//        System.out.println(count);
//    }
//    @Test
//    void esQueryByItem() {
//
//        String json="{\"delete\":false,\"esPage\":0,\"indexCode\":20,\"page\":1,"
//                + "\"selectForAndList\":[{\"extField\":false,\"fieldName\":\"sourceProductInstanceId\","
//                + "\"innerRequestType\":1,\"queryType\":1,\"values\":[100000]},{\"extField\":false,"
//                + "\"fieldName\":\"merchantId\",\"innerRequestType\":1,\"queryType\":1,\"values\":[100000]},"
//                + "{\"extField\":false,\"fieldName\":\"bosId\",\"innerRequestType\":1,\"queryType\":1,"
//                + "\"values\":[100000]},{\"extField\":false,\"fieldName\":\"itemId\",\"innerRequestType\":1,"
//                + "\"queryType\":2,\"values\":[1037910000]},{\"extField\":false,\"fieldName\":\"isDeleted\","
//                + "\"innerRequestType\":1,\"queryType\":1,\"values\":[false]}],\"size\":20,"
//                + "\"sortRequestDTOList\":[{\"extField\":false,\"sortFieldName\":\"itemCreateTime\",\"sortType\":2}],"
//                + "\"sourceProductId\":100000,\"supplementaryExtInfo\":false,\"tcode\":\"weimob\"}";
//        EsRequestDTO esRequestDTO =JSON.parseObject(json, EsRequestDTO.class);
//        SoaResponse<EsResponseDTO<EsItemResponseDTO>, SoaRespCommonErrorDTO> soaResponse = esQueryApi
//                .esQueryByItem(esRequestDTO);
//
//        System.out.println(JSON.toJSONString(soaResponse));
//    }
//    @Test
//    void esQueryByItemSku() {
//
//        String json="{\"delete\":false,\"esPage\":0,\"indexCode\":21,\"page\":1,"
//                + "\"selectForAndList\":[{\"extField\":false,\"fieldName\":\"sourceProductInstanceId\","
//                + "\"innerRequestType\":1,\"queryType\":1,\"values\":[100000]},{\"extField\":false,"
//                + "\"fieldName\":\"merchantId\",\"innerRequestType\":1,\"queryType\":1,\"values\":[100000]},"
//                + "{\"extField\":false,\"fieldName\":\"bosId\",\"innerRequestType\":1,\"queryType\":1,"
//                + "\"values\":[100000]},{\"extField\":false,\"fieldName\":\"vid\",\"innerRequestType\":1,"
//                + "\"queryType\":1,\"values\":[100000]},{\"extField\":false,\"fieldName\":\"isDeleted\","
//                + "\"innerRequestType\":1,\"queryType\":1,\"values\":[false]}],\"size\":20,"
//                + "\"sortRequestDTOList\":[{\"extField\":false,\"sortType\":1}],\"sourceProductId\":100000,"
//                + "\"supplementaryExtInfo\":false,\"tcode\":\"weimob\"}";
//        EsRequestDTO esRequestDTO =JSON.parseObject(json, EsRequestDTO.class);
//        SoaResponse<EsResponseDTO<EsItemSkuResponseDTO>, SoaRespCommonErrorDTO> soaResponse = esQueryApi
//                .esQueryByItemSku(esRequestDTO);
//
//        System.out.println(JSON.toJSONString(soaResponse));
//    }
//}