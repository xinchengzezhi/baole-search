package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EsSkuSpecValueFieldEnum {

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    SPEC_ID("specId"),

    SPEC_VALUE_ID("specValueId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    DICT_VALUE("dictValue"),

    SORT("sort"),

    IS_VALID("isValid"),

    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsSkuSpecValueFieldEnum fieldEnum : EsSkuSpecValueFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
