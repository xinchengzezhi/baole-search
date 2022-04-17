package com.baole.search.dto.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * es 查询方式
 */
@Getter
@AllArgsConstructor
public enum EsQueryEnum {

    /**
     * 匹配
     */
    TERM(1),

    /**
     * 多值匹配
     */
    TERMS(2),

    /**
     * 存在匹配
     */
    EXIST(4),

    /**
     * 结构匹配
     */
    NESTED(5),

    /**
     * 范围匹配
     */
    RANGE(6),

    /**
     * 短语匹配
     */
    MATCH_PHRASE(7),

    /**
     * 多值匹配，完全精准匹配
     */
    TERMS_PRECISE(8),

    /**
     * 内联查询
     */
    INNER(100),

    ;

    private final int queryType;
}
