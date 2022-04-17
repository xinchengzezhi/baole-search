package com.baole.search.dto.es.enums.agg;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 聚合查询返回 key 枚举
 */
@Getter
@AllArgsConstructor
public enum EsSumAggEnum {

    /**
     * sum 值
     */
    SUM_VALUE("sumValue"),

    ;

    private final String fieldName;

}
