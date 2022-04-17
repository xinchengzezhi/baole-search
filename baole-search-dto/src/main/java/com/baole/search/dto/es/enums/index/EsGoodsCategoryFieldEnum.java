package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EsGoodsCategoryFieldEnum {

    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    NAME("name"),

    CATEGORY_GROUP("categoryGroup"),

    NAME_RAW("name.raw"),

    PARENT_CATEGORY_ID("parentCategoryId"),

    CATEGORY_LEVEL("categoryLevel"),

    IS_DELETED("isDeleted"),

    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsGoodsCategoryFieldEnum fieldEnum : EsGoodsCategoryFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
