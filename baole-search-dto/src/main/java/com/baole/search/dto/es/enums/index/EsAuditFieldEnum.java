package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 审核检索字段
 */
@Getter
@AllArgsConstructor
public enum EsAuditFieldEnum {
    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    APPLY_ID("applyId"),

    BUSINESS_TYPE("businessType"),

    AUDIT_STATUS("auditStatus"),

    IS_DELETED("isDeleted"),

    CREATE_TIME("createTime"),

    UPDATE_TIME("updateTime"),
    ;
    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsAuditFieldEnum fieldEnum : EsAuditFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.getFieldName())) {
                return true;
            }
        }

        return false;
    }
}
