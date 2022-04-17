package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 操作单据检索字段
 */
@Getter
@AllArgsConstructor
public enum WarehouseFieldEnum {

    /**
     * 业务单据所属门店id
     * <p>
     * warehouse_business_receipt.vid
     */
    VID("vid"),

    /**
     * 树id
     * <p>
     * warehouse_operate_receipt.bos_id
     */
    BOS_ID("bosId"),

    /**
     * 单据仓库id
     * <p>
     * warehouse_operate_receipt.warehouse_id
     */
    WAREHOUSE_ID("warehouseId"),

    /**
     * 仓库类型
     * <p>
     * warehouse_operate_receipt.warehouse_type
     */
    WAREHOUSE_TYPE("warehouseType"),

    /**
     * 仓库属性列表
     * <p>
     * propertyList
     */
    PROPERTY_LIST("propertyList"),

    /**
     * 仓库属性列表key
     * <p>
     * propertyList.propertyK
     */
    PROPERTY_LIST_KEY("propertyList.propertyK"),

    /**
     * 仓库属性列表value
     * <p>
     * propertyList.propertyV
     */
    PROPERTY_LIST_VALUE("propertyList.propertyV"),

    /**
     * 是否有效
     */
    IS_INVALID("isInvalid"),
    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        for (WarehouseFieldEnum fieldEnum : WarehouseFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
