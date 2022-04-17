package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EsSkuSpecFieldEnum {

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    SPEC_ID("specId"),

    CATEGORY_ID("categoryId"),

    SPEC_NAME("specName"),

    SORT("sort"),

    SPEC_CODE("specCode"),

    CREATE_TIME("createTime"),

    UPDATE_TIME("updateTime"),

    IS_DELETED("isDeleted"),

    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsSkuSpecFieldEnum fieldEnum : EsSkuSpecFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
