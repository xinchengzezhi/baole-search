//package com.baole.search.boot;
//
//import com.alibaba.fastjson.JSON;
//import com.google.common.collect.Lists;
//import com.baole.common.dto.SoaRespCommonErrorDTO;
//import com.baole.search.api.EsQueryApi;
//import com.baole.search.dto.es.enums.EsIndexEnum;
//import com.baole.search.dto.es.enums.EsQueryEnum;
//import com.baole.search.dto.es.enums.index.OperateReceiptFieldEnum;
//import com.baole.search.dto.es.request.EsEntityRequestDTO;
//import com.baole.search.dto.es.request.EsRequestDTO;
//import com.baole.search.dto.es.response.EsResponseDTO;
//import com.baole.search.dto.es.response.entity.EsOperateReceiptItemResponseDTO;
//import com.baole.search.dto.es.response.entity.EsOperateReceiptResponseDTO;
//import com.weimob.soa.common.response.SoaResponse;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.annotation.Resource;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ReceiptTest {
//
//
//    @Resource
//    private EsQueryApi esQueryApi;
//
//
//    @org.junit.Test
//    public void receipt() throws InterruptedException {
//
//
//        EsRequestDTO esRequestDTO = new EsRequestDTO();
//        esRequestDTO.setTcode("Z15401");
//        esRequestDTO.setSourceProductId(111L);
//        esRequestDTO.setDelete(true);
//        esRequestDTO.setIndexCode(EsIndexEnum.OPERATE_RECEIPT_INDEX.getIndexCode());
//        esRequestDTO.setPage(1);
//        esRequestDTO.setSize(2);
//        esRequestDTO.setSelectForAndList(buildEsEntityRequestDTOList());
//
//
//
//        SoaResponse<EsResponseDTO<EsOperateReceiptResponseDTO>, SoaRespCommonErrorDTO> soaResponse =
//                esQueryApi.esQueryOperateReceiptByCondition(esRequestDTO);
//        System.out.println(soaResponse);
//
//    }
//
//
//    @org.junit.Test
//    public void receiptItem() throws InterruptedException {
//
//
//        EsRequestDTO esRequestDTO = new EsRequestDTO();
//        esRequestDTO.setTcode("Z15401");
//        esRequestDTO.setSourceProductId(111L);
//        esRequestDTO.setDelete(true);
//        esRequestDTO.setIndexCode(EsIndexEnum.OPERATE_RECEIPT_ITEM_INDEX.getIndexCode());
//        esRequestDTO.setPage(1);
//        esRequestDTO.setSize(2);
//        esRequestDTO.setSelectForAndList(buildEsEntityRequestDTOList());
//
//
//
//        SoaResponse<EsResponseDTO<EsOperateReceiptItemResponseDTO>, SoaRespCommonErrorDTO> soaResponse =
//                esQueryApi.esQueryOperateReceiptItemByCondition(esRequestDTO);
//        System.out.println(soaResponse);
//
//    }
//
//    @org.junit.Test
//    public void businessReceiptItem() throws InterruptedException {
//
//
//        EsRequestDTO esRequestDTO = new EsRequestDTO();
//        esRequestDTO.setTcode("Z15401");
//        esRequestDTO.setSourceProductId(111L);
//        esRequestDTO.setDelete(true);
//        esRequestDTO.setIndexCode(EsIndexEnum.BUSINESS_RECEIPT_ITEM_INDEX.getIndexCode());
//        esRequestDTO.setPage(1);
//        esRequestDTO.setSize(2);
//        esRequestDTO.setSelectForAndList(buildBusinessEsEntityRequestDTOList());
//
//
//
//        SoaResponse<EsResponseDTO<EsOperateReceiptItemResponseDTO>, SoaRespCommonErrorDTO> soaResponse =
//                esQueryApi.esQueryOperateReceiptItemByCondition(esRequestDTO);
//        System.out.println(soaResponse);
//
//    }
//
//    /**
//     * 组装条件
//     * @param
//     * @return
//     */
//    private List<EsEntityRequestDTO> buildEsEntityRequestDTOList() {
//        List<EsEntityRequestDTO> esEntityRequestDTOList = Lists.newArrayList();
//        EsEntityRequestDTO esEntityRequestDTO = new EsEntityRequestDTO();
//        esEntityRequestDTO.setFieldName(OperateReceiptFieldEnum.VID.getFieldName());
//        esEntityRequestDTO.setValues(Collections.singletonList(500512));
//        esEntityRequestDTO.setExtField(false);
//        esEntityRequestDTO.setQueryType(EsQueryEnum.TERM.getQueryType());
//        esEntityRequestDTOList.add(esEntityRequestDTO);
//        return esEntityRequestDTOList;
//    }
//
//    /**
//     * 组装条件
//     * @param
//     * @return
//     */
//    private List<EsEntityRequestDTO> buildBusinessEsEntityRequestDTOList() {
//        List<EsEntityRequestDTO> esEntityRequestDTOList = Lists.newArrayList();
//        EsEntityRequestDTO esEntityRequestDTO = new EsEntityRequestDTO();
//        esEntityRequestDTO.setFieldName(OperateReceiptFieldEnum.VID.getFieldName());
//        esEntityRequestDTO.setValues(Collections.singletonList(1));
//        esEntityRequestDTO.setExtField(false);
//        esEntityRequestDTO.setQueryType(EsQueryEnum.TERM.getQueryType());
//        esEntityRequestDTOList.add(esEntityRequestDTO);
//        return esEntityRequestDTOList;
//    }
//
//
//
//}
