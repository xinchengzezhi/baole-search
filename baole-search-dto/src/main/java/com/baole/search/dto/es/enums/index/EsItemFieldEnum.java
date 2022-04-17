package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 后台商品检索字段
 */
@Getter
@AllArgsConstructor
public enum EsItemFieldEnum {

    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    ITEM_ID("itemId"),

    ITEM_CODE("itemCode"),

    GOODS_TYPE("goodsType"),

    SUB_GOODS_TYPE("subGoodsType"),

    TITLE("title"),

    TITLE_RAW("title.raw"),

    OUTER_GOODS_ID("outerGoodsId"),

    OUTER_GOODS_CODE("outerGoodsCode"),

    OUTER_GOODS_CODE_PHRASE("outerGoodsCode.phrase"),

    CATEGORY_ID("categoryId"),

    CATEGORY_ID_LIST("categoryIdList"),

    ITEM_PROPERTY_VALUE_ID_LIST("itemPropertyValueIdList"),

    BRAND_ID("brandId"),

    IS_COMBINE("isCombine"),

    RELEASE_STATUS("releaseStatus"),

    IS_DELETED("isDeleted"),

    ITEM_CREATE_TIME("itemCreateTime"),

    ITEM_UPDATE_TIME("itemUpdateTime"),

    IS_ONLINE("isOnline"),

    IS_ASSIGNED("isAssigned"),

    CLASSIFY_ID_LIST("classifyIdList"),

    PROPERTY_ID_LIST("propertyIdList"),

    MAX_UNIFIED_PRICE("maxUnifiedPrice"),

    MIM_UNIFIED_PRICE("minUnifiedPrice"),

    IS_MULTI_SKU("isMultiSku"),

    IS_CURRENT_VID_CREATE("isCurrentVidCreate"),

    SYSTEM_WAREHOUSE_SELL_QUANTITY("systemWarehouseSellQuantity"),

    SYSTEM_WAREHOUSE_DEFECTIVE_QUANTITY("systemWarehouseDefectiveQuantity"),

    // skuDetailList
    SKU_DETAIL_LIST("skuDetailList"),

    SKU_ID("skuDetailList.skuId"),

    SKU_BAR_CODE("skuDetailList.skuBarCode"),

    SKU_BAR_CODE_PHRASE("skuDetailList.skuBarCode.phrase"),

    OUTER_SKU_CODE("skuDetailList.outerSkuCode"),

    OUTER_SKU_CODE_PHRASE("skuDetailList.outerSkuCode.phrase"),

    UNIFIED_PRICE("skuDetailList.unifiedPrice"),

    MARKET_PRICE("skuDetailList.marketPrice"),

    COST_PRICE("skuDetailList.costPrice"),

    SKU_IS_DELETED("skuDetailList.isDeleted"),

    PARENT_UNIFIED_PRICE("skuDetailList.parentUnifiedPrice"),

    PARENT_MARKET_PRICE("skuDetailList.parentMarketPrice"),

    PARENT_COST_PRICE("skuDetailList.parentCostPrice"),

    SKU_SYSTEM_WAREHOUSE_SELL_QUANTITY("skuDetailList.systemWarehouseSellQuantity"),

    SKU_SYSTEM_WAREHOUSE_DEFECTIVE_QUANTITY("skuDetailList.systemWarehouseDefectiveQuantity"),
    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsItemFieldEnum fieldEnum : EsItemFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
