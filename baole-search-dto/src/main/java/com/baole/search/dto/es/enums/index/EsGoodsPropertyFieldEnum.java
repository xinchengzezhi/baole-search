package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EsGoodsPropertyFieldEnum {

    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    VID("vid"),

    PROP_ID("propId"),

    PROP_NAME("propName"),

    PROP_TYPE("propType"),

    SORT("sort"),

    IS_DELETED("isDeleted"),

    CATEGORY_ID("categoryId"),

    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsGoodsPropertyFieldEnum fieldEnum : EsGoodsPropertyFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
