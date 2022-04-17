package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 操作单据检索字段
 */
@Getter
@AllArgsConstructor
public enum StockWarningFieldEnum {

    /**
     * 业务单据所属门店id
     * <p>
     */
    WAREHOUSE_CREATE_VID("warehouseCreateVid"),

    /**
     * 树id
     * <p>
     */
    BOS_ID("bosId"),

    /**
     * item_title
     * <p>
     */
    ITEM_TITLE("itemTitle"),

    /**
     * itemSkuProductCode
     * <p>
     */
    ITEM_SKU_PRODUCT_CODE("itemSkuProductCode"),

    /**
     * goodsType
     * <p>
     */
    GOODS_TYPE("goodsType"),

    /**
     * warningType
     */
    WARNING_TYPE("warningType"),

    /**
     * ruleType
     */
    RULE_TYPE("ruleType"),

    /**
     * 是否有效
     */
    IS_DELETED("isDeleted"),

    /**
     * 是否有效
     */
    VALID_TYPE("validType"),

    /**
     * 预警更新时间
     */
    WARNING_UPDATE_TIME("warningUpdateTime"),
    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        for (StockWarningFieldEnum fieldEnum : StockWarningFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
