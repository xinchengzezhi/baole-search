package com.baole.search.dto.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * es 范围查询类型
 */
@Getter
@AllArgsConstructor
public enum EsRangeEnum {

    /**
     * 开区间
     */
    NORMAL(1),

    /**
     * 闭区间
     */
    INCLUDE(2),

    ;

    private final int rangeType;
}
