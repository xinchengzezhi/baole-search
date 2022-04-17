package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 业务单据检索字段
 */
@Getter
@AllArgsConstructor
public enum OperateReceiptItemFieldEnum {

    /**
     * 业务单据id
     * <p>
     * warehouse_business_receipt.receipt_id
     */
    BUSINESS_RECEIPT_ID("businessReceiptId"),

    /**
     * 外部业务单据id
     * <p>
     * warehouse_business_receipt.out_receipt_id
     */
    OUT_RECEIPT_ID("outReceiptId"),

    /**
     * 业务单据所属门店id
     * <p>
     * warehouse_business_receipt.vid
     */
    VID("vid"),

    /**
     * 业务单据状态
     * <p>
     * warehouse_business_receipt.status
     */
    BUSINESS_RECEIPT_STATUS("businessReceiptStatus"),

    /**
     * 关联业务单号
     * <p>
     * warehouse_business_receipt.receipt_id
     * warehouse_business_receipt.out_receipt_id
     */
    RELATION_RECEIPT_ID("relationReceiptId"),


    /**
     * 操作单据号
     * <p>
     * warehouse_operate_receipt.receipt_id
     */
    OPERATE_RECEIPT_ID("operateReceiptId"),

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
     * 调拨、采购、盘点、销售、售后、调整
     * <p>
     * warehouse_operate_receipt.biz_type
     */
    BIZ_TYPE("bizType"),

    /**
     * 操作类型：出库、入库
     * <p>
     * warehouse_operate_receipt.operate_type
     */
    OPERATE_TYPE("operateType"),

    /**
     * 操作人id
     * <p>
     * warehouse_operate_receipt.operate_id
     */
    OPERATE_ID("operateId"),


    /**
     * 单据商品创建时间
     * <p>
     * warehouse_operate_receipt_item_relation.create_time
     */
    ITEM_RELATION_CREATE_TIME("itemRelationCreateTime"),
    /**
     * 单品名称
     * <p>
     * warehouse_business_receipt_item_relation.item_snapshot.title
     */
    TITLE("title"),

    /**
     * 单品条码
     * <p>
     * warehouse_business_receipt_item_relation.item_snapshot.productCode
     */
    PRODUCT_CODE("productCode"),

    /**
     * itemSkuId
     * <p>
     * warehouse_business_receipt_item_relation.item_snapshot.item_sku_id
     */
    ITEM_SKU_ID("itemSkuId"),

    /**
     * batchNumber
     * <p>
     * warehouse_business_receipt_item_relation.item_snapshot.batch_number
     */
    BATCH_NUMBER("batchNumber"),

    /**
     * 创建商品vid
     * <p>
     * warehouse_business_receipt_item_relation.item_snapshot.source_vid
     */
    SOURCE_VID("sourceVid"),

    /**
     * 是否删除
     */
    IS_DELETED("isDeleted"),
    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        for (OperateReceiptItemFieldEnum fieldEnum : OperateReceiptItemFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
