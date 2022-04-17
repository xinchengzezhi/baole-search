package com.baole.search.dto.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * es 排序
 */
@Getter
@AllArgsConstructor
public enum EsSortEnum {

    /**
     * 正序
     */
    ASC(0),

    /**
     * 倒序
     */
    DESC(1),

    ;

    private final int sortType;
}
