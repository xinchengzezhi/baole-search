package com.baole.search.dto.es.enums.agg;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EsDistinctCountEnum {

    /**
     * 去重后总数
     */
    DISTINCT_COUNT("distinctCount"),

    ;

    private final String fieldName;
}
