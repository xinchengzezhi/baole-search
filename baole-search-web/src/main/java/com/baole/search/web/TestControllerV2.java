package com.baole.search.web;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baole.common.elasticsearch.ElasticsearchRestService;
import com.baole.common.elasticsearch.SearchRequest;
import com.baole.search.api.EsQueryApi;
import com.baole.search.domain.bo.es.EsGoodsInfoBO;
import com.baole.search.dto.es.request.EsRequestDTO;
import com.baole.search.dto.es.response.EsResponseDTO;
import com.baole.search.dto.es.response.PageResult;
import com.baole.search.dto.es.response.SoaRespCommonErrorDTO;
import com.baole.search.dto.es.response.SoaResponse;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import com.baole.search.dto.es.response.entity.EsItemSkuResponseDTO;
import com.baole.search.server.es.EsQueryProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class TestControllerV2 {

    @Resource
    private EsQueryProcessor<EsGoodsInfoBO, EsGoodsResponseDTO> esQueryProcessor;
    @Autowired
    private ElasticsearchRestService elasticsearchRestService;


    @RequestMapping("/test")
    public String test() {
        return "hello";
    }

    @RequestMapping("/test2")
    public String test2() {
        String json = "{\"delete\":false,\"esPage\":0,\"indexCode\":21,\"page\":1,"
                + "\"selectForAndList\":[{\"extField\":false,\"fieldName\":\"sourceProductInstanceId\","
                + "\"innerRequestType\":1,\"queryType\":1,\"values\":[2049000]},{\"extField\":false,"
                + "\"fieldName\":\"merchantId\",\"innerRequestType\":1,\"queryType\":1,\"values\":[2000000016212]},"
                + "{\"extField\":false,\"fieldName\":\"bosId\",\"innerRequestType\":1,\"queryType\":1,"
                + "\"values\":[4000136704212]},{\"extField\":false,\"fieldName\":\"vid\",\"innerRequestType\":1,"
                + "\"queryType\":1,\"values\":[6000220771212]}],\"size\":10,"
                + "\"sortRequestDTOList\":[{\"extField\":false,\"sortFieldName\":\"skuCreateTime\",\"sortType\":1}],"
                + "\"sourceProductId\":145,\"supplementaryExtInfo\":false,\"tcode\":\"weimob\"}";
        EsRequestDTO esRequestDTO = JSON.parseObject(json, EsRequestDTO.class);
        EsResponseDTO<EsGoodsResponseDTO> response = esQueryProcessor.esQuery(esRequestDTO);
        return JSON.toJSONString(response);
    }

    @RequestMapping("/es")
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
        JSONObject search = elasticsearchRestService.search(req);
        PageResult<EsGoodsResponseDTO> result = elasticsearchRestService.parseDocumentSearchAfter(search, EsGoodsResponseDTO.class);
        return JSON.toJSONString(result);
    }

}
