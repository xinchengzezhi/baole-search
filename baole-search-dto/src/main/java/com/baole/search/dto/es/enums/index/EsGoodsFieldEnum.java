package com.baole.search.dto.es.enums.index;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * 商品检索字段
 */
@Getter
@AllArgsConstructor
public enum EsGoodsFieldEnum {

    ES_ID("_id"),
    SOURCE_PRODUCT_VERSION_ID("sourceProductVersionId"),

    SOURCE_PRODUCT_INSTANCE_ID("sourceProductInstanceId"),

    MERCHANT_ID("merchantId"),

    BOS_ID("bosId"),

    V_ID("vid"),

    GOODS_ID("goodsId"),

    ITEM_ID("itemId"),

    UNIT_ID("unitId"),

    GOODS_ID_SORT("goodsId.sort"),

    CATEGORY_ID("categoryId"),

    GOODS_TYPE("goodsType"),

    SUB_GOODS_TYPE("subGoodsType"),

    SOLD_TYPE("soldType"),

    INDUSTRY_GOODS_TYPE("industryGoodsType"),

    DEPOSIT_PAY_TYPE("depositPayType"),

    OUTER_GOODS_CODE("outerGoodsCode"),

    OUTER_GOODS_CODE_PHRASE("outerGoodsCode.phrase"),

    TITLE("title"),

    TITLE_RAW("title.raw"),

    REAL_GOODS_SALES("realGoodsSales"),

    FRONT_GOODS_SALES("frontGoodsSales"),

    PAY_GOODS_SALES("payGoodsSales"),

    CHANNEL_LIST_SIZE("channelListSize"),

    SELL_QUANTITY("sellQuantity"),

    SELL_QUANTITY_UPDATE_TIME("sellQuantityUpdateTime"),

    PRE_SELL_QUANTITY("preSellQuantity"),

    PRE_SELL_QUANTITY_UPDATE_TIME("preSellQuantityUpdateTime"),

    GOODS_CREATE_TIME("goodsCreateTime"),

    GOODS_UPDATE_TIME("goodsUpdateTime"),

    SITE_GOODS_CREATE_TIME("siteGoodsCreateTime"),

    SITE_GOODS_UPDATE_TIME("siteGoodsUpdateTime"),

    ONLINE_DATE("onlineDate"),

    IS_ONLINE("isOnline"),

    IS_CAN_SELL("isCanSell"),

    IS_DELETED("isDeleted"),

    IS_COMBINE("isCombine"),

    IS_CURRENT_V_ID_CREATE("isCurrentVidCreate"),

    PROPERTY_ID_LIST("propertyIdList"),

    TAG_ID_LIST("tagIdList"),

    TAG_CODE_LIST("tagCodeList"),

    CATEGORY_ID_LIST("categoryIdList"),

    BRAND_ID("brandId"),

    CLASSIFY_ID_LIST("classifyIdList"),

    GOODS_PROPERTY_VALUE_ID_LIST("goodsPropertyValueIdList"),

    ABILITY_CODE_LIST("abilityCodeList"),

    SORT("sort"),

    SITE_SORT("siteSort"),

    IS_ASSIGNED("isAssigned"),

    // skuDetailList

    SKU_DETAIL_LIST("skuDetailList"),

    SKU_ID("skuDetailList.skuId"),

    SKU_BAR_CODE("skuDetailList.skuBarCode"),

    SKU_BAR_CODE_PHRASE("skuDetailList.skuBarCode.phrase"),

    OUTER_SKU_CODE("skuDetailList.outerSkuCode"),

    OUTER_SKU_CODE_PHRASE("skuDetailList.outerSkuCode.phrase"),

    SPEC_ID_LIST("skuDetailList.specIdList"),

    SPEC_VALUE_ID_LIST("skuDetailList.specValueIdList"),

    // setDetailList

    SET_DETAIL_LIST("setDetailList"),

    SET_ID("setDetailList.setId"),

    GROUP_ID("setDetailList.groupId"),

    GROUP_SORT("setDetailList.groupSort"),

    MAX_POINT("setDetailList.maxPoint"),

    MIN_POINT("setDetailList.minPoint"),

    SET_SALES_NUM("setDetailList.salesNum"),

    SET_MAX_PRICE("setDetailList.maxPrice"),

    SET_MIN_PRICE("setDetailList.minPrice"),

    SET_GOODS_CREATE_TIME("setDetailList.setGoodsCreateTime"),

    // channelList

    CHANNEL_LIST("channelList"),

    CHANNEL_ID("channelList.channelId"),

    CHANNEL_MAX_PRICE("channelList.channelMaxPrice"),

    CHANNEL_MIN_PRICE("channelList.channelMinPrice"),

    // priceList

    PRICE_LIST("priceList"),

    PRICE_CHANNEL_ID("priceList.channelId"),

    PRICE_SET_ID("priceList.setId"),

    PRICE_TYPE("priceList.priceType"),

    PRICE_MAX_PRICE("priceList.maxPrice"),

    PRICE_MIN_PRICE("priceList.minPrice"),

    // deliveryInfoList

    DELIVERY_INFO_LIST("deliveryInfoList"),

    TEMPLATE_ID("deliveryInfoList.templateId"),

    DELIVERY_TYPE("deliveryInfoList.deliveryType"),

    // stockList
    STOCK_LIST("stockList"),

    STOCK_TYPE("stockList.stockType"),

    STOCK_NUM("stockList.stockNum"),

    STOCK_CODE("stockList.stockCode"),

    STOCK_CHANNEL_ID("stockList.channelId"),

    // limitList

    LIMIT_LIST("limitList"),

    LIMIT_TYPE("limitList.limitType"),

    LIMIT_CONVERTIBLE_NUM("limitList.limitConvertibleNum"),

    ;

    private final String fieldName;

    public static boolean checkFieldName(String name) {

        if (name == null) {
            return false;
        }

        for (EsGoodsFieldEnum fieldEnum : EsGoodsFieldEnum.values()) {

            if (Objects.equals(name, fieldEnum.fieldName)) {
                return true;
            }
        }

        return false;
    }
}
