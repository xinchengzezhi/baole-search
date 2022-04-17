package com.baole.search.dto.es.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * es 返回值
 *
 * @param <T> 基础查询返回类型
 */
@Getter
@Setter
@ToString
public class EsResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 5510637538573464407L;

    /**
     * 符合条件总数
     */
    private long totalHits;

    /**
     * 分页返回基础查询结果
     */
    private List<T> responseList;

    /**
     * 返回聚合查询结果
     */
    private List<EsAggregationResponseDTO> aggregationResponseList;

    /**
     * 滚动游标，滚动查询时返回
     */
    private String scrollId;
}