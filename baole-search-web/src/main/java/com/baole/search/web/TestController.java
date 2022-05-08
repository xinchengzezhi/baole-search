package com.baole.search.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baole.search.api.EsQueryApi;
import com.baole.common.elasticsearch.ElasticsearchRestService;
import com.baole.search.domain.bo.es.EsGoodsInfoBO;
import com.baole.search.dto.es.response.PageResult;
import com.baole.common.elasticsearch.SearchRequest;
import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.SoaRespCommonErrorDTO;
import com.baole.search.dto.es.response.SoaResponse;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import com.baole.search.dto.es.response.entity.EsItemSkuResponseDTO;
import com.baole.search.server.es.BBossQueryProcessor;
import com.baole.search.server.es.EsQueryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestController {

    //    @Autowired
//    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private EsQueryProcessor<EsGoodsInfoBO, EsGoodsResponseDTO> esQueryProcessor;
//    @Autowired
//    private ElasticsearchRestService elasticsearchRestService;


//
//    @RequestMapping("/test3")
//    public String test2() {
//        String json = "{\"delete\":false,\"esPage\":0,\"indexCode\":21,\"page\":1,"
//                + "\"selectForAndList\":[{\"extField\":false,\"fieldName\":\"sourceProductInstanceId\","
//                + "\"innerRequestType\":1,\"queryType\":1,\"values\":[2049000]},{\"extField\":false,"
//                + "\"fieldName\":\"merchantId\",\"innerRequestType\":1,\"queryType\":1,\"values\":[2000000016212]},"
//                + "{\"extField\":false,\"fieldName\":\"bosId\",\"innerRequestType\":1,\"queryType\":1,"
//                + "\"values\":[4000136704212]},{\"extField\":false,\"fieldName\":\"vid\",\"innerRequestType\":1,"
//                + "\"queryType\":1,\"values\":[6000220771212]}],\"size\":10,"
//                + "\"sortRequestDTOList\":[{\"extField\":false,\"sortFieldName\":\"skuCreateTime\",\"sortType\":1}],"
//                + "\"sourceProductId\":145,\"supplementaryExtInfo\":false,\"tcode\":\"weimob\"}";
//        EsRequestDTO esRequestDTO = JSON.parseObject(json, EsRequestDTO.class);
//        SoaResponse<EsResponseDTO<EsItemSkuResponseDTO>, SoaRespCommonErrorDTO> response = esQueryApi.esQueryByItemSku(esRequestDTO);
////        SoaResponse<PageResult<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> response = esQueryApi
////                .esSearchAfterByGoods(esRequestDTO);
//        return JSON.toJSONString(response);
//    }

//    @RequestMapping("/es")
    public String es() {
        SearchRequest req = new SearchRequest();
        req.setIndex("saas-mp-goods-info");
        req.setDsl("{\n"
                + "  \"query\": {\n"
                + "   \"match_all\": {}\n"
                + "  },\n"
                + "  \n"
                + "  \"sort\" : [\n"
                + "      {\n"
                + "         \"channelList.channelMaxPrice\": {\n"
                + "           \n"
                + "            \"order\" : \"desc\",\n"
                + "            \"nested\": {\n"
                + "               \"path\": \"channelList\",\n"
                + "               \"filter\": {\n"
                + "                  \"term\": {\"channelList.channelId\":10001}\n"
                + "               }\n"
                + "              \n"
                + "            }\n"
                + "         }\n"
                + "      }\n"
                + "   ]\n"
                + "}");
//        JSONObject search = elasticsearchRestService.search(req);
//        PageResult<EsGoodsResponseDTO> result = elasticsearchRestService.parseDocumentSearchAfter(search, EsGoodsResponseDTO.class);
        return JSON.toJSONString(null);
    }


    @RequestMapping("/sort")
    public String sort() {
        String json = "{\n"
                + "    \"delete\":false,\n"
                + "    \"esPage\":0,\n"
                + "    \"indexCode\":1,\n"
                + "    \"page\":1,\n"
                + "    \"selectForAndList\":[\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"fieldName\":\"sourceProductInstanceId\",\n"
                + "            \"innerRequestType\":1,\n"
                + "            \"queryType\":1,\n"
                + "            \"values\":[\n"
                + "                2049000\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"fieldName\":\"merchantId\",\n"
                + "            \"innerRequestType\":1,\n"
                + "            \"queryType\":1,\n"
                + "            \"values\":[\n"
                + "                10000\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"fieldName\":\"bosId\",\n"
                + "            \"innerRequestType\":1,\n"
                + "            \"queryType\":1,\n"
                + "            \"values\":[\n"
                + "                50000\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"fieldName\":\"vid\",\n"
                + "            \"innerRequestType\":1,\n"
                + "            \"queryType\":1,\n"
                + "            \"values\":[\n"
                + "                120000\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"fieldName\":\"isDeleted\",\n"
                + "            \"innerRequestType\":1,\n"
                + "            \"queryType\":1,\n"
                + "            \"values\":[\n"
                + "                false\n"
                + "            ]\n"
                + "        },\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"innerRequestList\":[\n"
                + "                {\n"
                + "                    \"extField\":false,\n"
                + "                    \"innerRequestList\":[\n"
                + "                        {\n"
                + "                            \"extField\":false,\n"
                + "                            \"innerRequestList\":[\n"
                + "                                {\n"
                + "                                    \"extField\":false,\n"
                + "                                    \"fieldName\":\"channelList.channelId\",\n"
                + "                                    \"innerRequestType\":1,\n"
                + "                                    \"queryType\":2,\n"
                + "                                    \"values\":[\n"
                + "                                        10001,\n"
                + "                                        10002\n"
                + "                                    ]\n"
                + "                                }\n"
                + "                            ],\n"
                + "                            \"innerRequestType\":1,\n"
                + "                            \"nestedPath\":\"channelList\",\n"
                + "                            \"queryType\":5\n"
                + "                        },\n"
                + "                        {\n"
                + "                            \"extField\":false,\n"
                + "                            \"fieldName\":\"channelListSize\",\n"
                + "                            \"innerRequestType\":1,\n"
                + "                            \"queryType\":1,\n"
                + "                            \"values\":[\n"
                + "                                2\n"
                + "                            ]\n"
                + "                        }\n"
                + "                    ],\n"
                + "                    \"innerRequestType\":1,\n"
                + "                    \"queryType\":100\n"
                + "                }\n"
                + "            ],\n"
                + "            \"innerRequestType\":2,\n"
                + "            \"queryType\":100\n"
                + "        }\n"
                + "    ],\n"
                + "    \"size\":20,\n"
                + "    \"sortRequestDTOList\":[\n"
                + "        {\n"
                + "            \"extField\":false,\n"
                + "            \"nestedSortPath\":\"channelList\",\n"
                + "            \"sortFieldName\":\"channelList.channelMinPrice\",\n"
                + "            \"sortType\":1,\n"
                + "            \"esNestedSortFilterRequestDTO\":{\n"
                + "                \"extField\":false,\n"
                + "                \"fieldName\":\"channelList.channelId\",\n"
                + "                \"value\":10001\n"
                + "            }\n"
                + "        }\n"
                + "    ],\n"
                + "    \"sourceProductId\":145,\n"
                + "    \"supplementaryExtInfo\":false,\n"
                + "    \"tcode\":\"weimob\"\n"
                + "}";
        EsRequestDTO esRequestDTO = JSON.parseObject(json, EsRequestDTO.class);
        esRequestDTO.setIndexName("saas-mp-goods-info");
        esRequestDTO.getSortRequestDTOList().stream().forEach(e->{
            e.setSelectForAndList(esRequestDTO.getSelectForAndList());
        });
//        esRequestDTO.setSearchAfter("1");
        EsResponseDTO<EsGoodsResponseDTO> responseDTO = esQueryProcessor.esQuery(esRequestDTO);
//        SoaResponse<PageResult<EsGoodsResponseDTO>, SoaRespCommonErrorDTO> response = esQueryApi
//                .esSearchAfterByGoods(esRequestDTO);
        return JSON.toJSONString(responseDTO);
    }

}
