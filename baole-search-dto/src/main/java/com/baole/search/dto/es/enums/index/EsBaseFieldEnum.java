package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 基础字段
 */
@Getter
@AllArgsConstructor
public enum EsBaseFieldEnum {

    TCODE("tcode"),

    SOURCE_PRODUCT_ID("sourceProductId"),

    IS_DELETED("isDeleted"),

    ;

    private final String fieldName;
}
