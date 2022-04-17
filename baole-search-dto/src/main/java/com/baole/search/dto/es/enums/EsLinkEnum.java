package com.baole.search.dto.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * es 查询连接方式
 */
@Getter
@AllArgsConstructor
public enum EsLinkEnum {

    /**
     * 与
     */
    AND(1),

    /**
     * 或
     */
    OR(2),

    /**
     * 非
     */
    NOT(3),

    ;

    private final int linkType;
}
