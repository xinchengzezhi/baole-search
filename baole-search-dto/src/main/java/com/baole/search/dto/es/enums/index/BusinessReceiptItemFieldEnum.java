package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 业务单据检索字段
 */
@Getter
@AllArgsConstructor
public enum BusinessReceiptItemFieldEnum {

    /**
     * bosId
     */
    BOS_ID("bosId"),

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
    BUSINESS_OUT_RECEIPT_ID("businessOutReceiptId"),

    /**
     * 业务单据所属门店id
     * <p>
     * warehouse_business_receipt.vid
     */
    VID("vid"),

    /**
     * 业务类型
     * warehouse_business_receipt.biz_type
     */
    BIZ_TYPE("bizType"),

    /**
     * 入库门店
     * warehouse_business_receipt.ext_json.inVid
     */
    IN_VID("inVid"),

    /**
     * 出库门店
     * warehouse_business_receipt.ext_json.outVid
     */
    OUT_VID("outVid"),

    /**
     * 入库仓库
     * warehouse_business_receipt.ext_json.inWarehouseId
     */
    IN_WAREHOUSE_ID("inWarehouseId"),

    /**
     * 出库仓库
     * warehouse_business_receipt.ext_json.outWarehouseId
     */
    OUT_WAREHOUSE_ID("outWarehouseId"),

    /**
     * 单据创建时间
     * <p>
     * warehouse_business_receipt.create_time
     */
    RECEIPT_RECEIPT_CREATE_TIME("receiptCreateTime"),

    /**
     * 操作人id
     * <p>
     * warehouse_business_receipt.operate_id
     */
    OPERATE_ID("operateId"),

    /**
     * 业务单据状态
     * <p>
     * warehouse_business_receipt.status
     */
    BUSINESS_RECEIPT_STATUS("businessReceiptStatus"),

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

        for (BusinessReceiptItemFieldEnum fieldEnum : BusinessReceiptItemFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
