package com.baole.search.dto.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * es 聚合查询枚举
 */
@Getter
@AllArgsConstructor
public enum EsAggregationEnum {

    /**
     * 去重聚合
     */
    DISTINCT(1, "EsDistinctAggEnum"),

    /**
     * 求和
     */
    SUM(2, "EsSumAggEnum"),

    /**
     * 去重后总数
     */
    DISTINCT_COUNT(3, "EsDistinctCountEnum"),

    ;

    private final int aggregationType;

    /**
     * @see com.baole.search.dto.es.enums.agg
     */
    private final String esAggEnumName;
}
