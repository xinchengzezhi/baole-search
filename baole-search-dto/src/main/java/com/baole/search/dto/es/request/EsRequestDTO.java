package com.baole.search.dto.es.request;

import com.baole.search.dto.es.enums.EsIndexEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * es 查询
 */
@Getter
@Setter
@ToString
public class EsRequestDTO implements Serializable {

    private static final long serialVersionUID = 7760710298355475404L;
    /**
     * es 索引
     *
     */
    private String indexName;

    /**
     * 查询 【and】 模型拼接
     */
    private List<EsEntityRequestDTO> selectForAndList;

    /**
     * 页码 >= 1
     */
    private int page = 1;

    /**
     * 页大小 > 0
     */
    private int size = 10;

    /**
     * 指定字段返回
     */
    private String[] sourceIncludes;


    /**
     * 排序查询
     */
    private List<EsSortRequestDTO> sortRequestDTOList;

    /**
     * 聚合查询 list
     */
    private List<EsAggregationRequestDTO> aggregationRequestDTOList;

    /**
     * 是否使用滚动查询，携带 scrollId 时，无需再次构造 selectForAndList 查询模型
     */
    private EsScrollRequestDTO esScrollRequestDTO;

    /**
     * searchAfter 根据排序字段生成，第一次无需传递，第二次将上次返回的searchAfter数值返回即可
     */
    private String searchAfter;

    public int getEsPage() {
        return page - 1;
    }
}
