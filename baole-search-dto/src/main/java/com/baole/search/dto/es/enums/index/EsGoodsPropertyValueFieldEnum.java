package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EsGoodsPropertyValueFieldEnum {

    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    PROP_ID("propId"),

    PROP_VALUE_ID("propValueId"),

    PROP_VALUE("propValue"),

    IS_DELETED("isDeleted"),

    CATEGORY_ID("categoryId"),
    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsGoodsPropertyValueFieldEnum fieldEnum : EsGoodsPropertyValueFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
