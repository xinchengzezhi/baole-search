package com.baole.search.dto.es.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * es 索引
 */
@Getter
@AllArgsConstructor
public enum EsIndexEnum {

    /**
     * 商品基础索引
     */
    MP_GOODS_INFO_INDEX("saas-mp-goods-info", "EsQueryForGoodsIndex"),

    /**
     * 后台商品
     */
    ITEM_INDEX("saas-mp-goods-item", "EsQueryForItemIndex"),

    ITEM_SKU_INDEX("saas-mp-goods-item-sku", "EsQueryForItemSkuIndex"),



    ;

    private final String indexName;

    private final String processorName;

    public static String getProcessorName(String indexCode) {

        for (EsIndexEnum indexEnum : EsIndexEnum.values()) {

            if (Objects.equals(indexCode, indexEnum.getIndexName())) {
                return indexEnum.getProcessorName();
            }
        }

        return null;
    }
}
