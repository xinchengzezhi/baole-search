package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 后台商品sku检索字段
 */
@Getter
@AllArgsConstructor
public enum EsItemSkuFieldEnum {

    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    PARENT_VID("parentVid"),

    ITEM_ID("itemId"),

    SKU_ID("skuId"),

    CATEGORY_ID("categoryId"),

    BRAND_ID("brandId"),

    CLASSIFY_ID_LIST("classifyIdList"),

    IS_COMBINE("isCombine"),

    ITEM_CREATE_TIME("itemCreateTime"),

    ITEM_UPDATE_TIME("itemUpdateTime"),

    RELEASE_STATUS("releaseStatus"),

    ITEM_TITLE("itemTitle"),

    ITEM_TITLE_RAW("itemTitle.raw"),

    GOODS_TYPE("goodsType"),

    SUB_GOODS_TYPE("subGoodsType"),

    OUTER_GOODS_ID("outerGoodsId"),

    OUTER_GOODS_CODE("outerGoodsCode"),

    OUTER_GOODS_CODE_PHRASE("outerGoodsCode.phrase"),

    SKU_BAR_CODE("skuBarCode"),

    SKU_BAR_CODE_PHRASE("skuBarCode.phrase"),

    OUTER_SKU_CODE("outerSkuCode"),

    OUTER_SKU_CODE_PHRASE("outerSkuCode.phrase"),

    SKU_TITLE("skuTitle"),

    SKU_TITLE_RAW("skuTitle.raw"),

    IS_DISABLED("isDisabled"),

    IS_DELETED("isDeleted"),

    UNIFIED_PRICE("unifiedPrice"),

    MARKET_PRICE("marketPrice"),

    COST_PRICE("costPrice"),

    SKU_CREATE_TIME("skuCreateTime"),

    SKU_UPDATE_TIME("skuUpdateTime"),

    UNIT_ID("unitId"),

    IS_CURRENT_VID_CREATE("isCurrentVidCreate"),

    SYSTEM_WAREHOUSE_SELL_QUANTITY("systemWarehouseSellQuantity"),

    SYSTEM_WAREHOUSE_DEFECTIVE_QUANTITY("systemWarehouseDefectiveQuantity"),

    TRANSPORTATION_QUANTITY("transportationQuantity"),
    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsItemSkuFieldEnum fieldEnum : EsItemSkuFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
