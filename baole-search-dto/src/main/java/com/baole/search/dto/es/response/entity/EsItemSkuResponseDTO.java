package com.baole.search.dto.es.response.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EsItemSkuResponseDTO extends EsExtResponseDTO implements Serializable {
    private static final long serialVersionUID = -5604529634444960462L;
    /**
     * 组织架构树 id
     * <p>
     * 新定义
     */
    private Long bosId;
    /**
     * 创建者的组织 id
     * <p>
     * ec-goods: parentVid
     */
    private Long parentVid;
    /**
     * 树节点 id
     * <p>
     * 新定义
     */
    private Long vid;
    /**
     * sku id
     * <p>
     * ec-goods: skuId
     */
    private Long itemId;
    /**
     * sku id
     * <p>
     * ec-goods: skuId
     */
    private Long skuId;
    /**
     * 叶子类目 id
     * <p>
     * ec-goods: categoryId
     */
    private Long categoryId;
    /**
     * 品牌 id
     */
    private Long brandId;
    /**
     * 后台分组 id list
     * <p>
     * ec-goods: classifyIdList
     */
    private List<Long> classifyIdList;

    /** 是否组合品 0 否 1是 **/
    private Boolean isCombine;

    /**
     * 发布状态 1 未发布 2审核中 3已发布
     */
    private Integer releaseStatus;
    /**
     * 商品创建时间
     * <p>
     * ec-goods: itemCreateTime
     */
    private Date itemCreateTime;

    /**
     * 商品更新时间
     * <p>
     * ec-goods: itemUpdateTime
     */
    private Date itemUpdateTime;
    /**
     * 商品标题
     */
    private String itemTitle;
    /**
     * 商品类型
     * <p>
     * ec-goods: goodsBizType, goodsPointType
     */
    private Integer goodsType;

    /**
     * 商品子类型
     * <p>
     * ec-goods: b2cGoodsType, subGoodsBizType
     */
    private Integer subGoodsType;
    /**
     * 外部商品 id
     */
    private Long outerGoodsId;

    /**
     * 外部商品 code
     * <p>
     * ec-goods: outerGoodsCode
     */
    private String outerGoodsCode;

    /**
     * sku 条码
     * <p>
     * ec-goods: skuBarCode
     */
    private String skuBarCode;

    /**
     * 外部 sku 条码
     * <p>
     * ec-goods: outerSkuCode
     */
    private String outerSkuCode;
    /**
     * sku标题
     * <p>
     * ec-goods: skuTitle
     */
    private String skuTitle;
    /**
     * 是否被禁用
     * <p>
     * ec-goods: isDisabled
     */
    private Boolean isDisabled;

    /**
     * 是否被删除
     * <p>
     * ec-goods: isDeleted
     */
    private Boolean isDeleted;

    /**
     * 商家统一价
     */
    private BigDecimal unifiedPrice;

    /**
     * 市场价
     */
    private BigDecimal marketPrice;

    /**
     * 成本价
     */
    private BigDecimal costPrice;

//    /**
//     * 规格list
//     */
//    private List<EsSkuSpecValueDetailResponseDTO> specList;

    /**
     * sku创建时间
     * <p>
     * ec-goods: itemCreateTime
     */
    private Date skuCreateTime;

    /**
     * sku更新时间
     * <p>
     * ec-goods: itemUpdateTime
     */
    private Date skuUpdateTime;

    /** 计量单位ID，对应的是商户配置表中的数据 **/
    private Long unitId;

    /**
     * 商品归属（子门店、主门店） true 当前门店创建商品 false 主门店创建商品
     */
    private Boolean isCurrentVidCreate;

    /**
     * 系统仓-可售库存
     */
    private BigDecimal systemWarehouseSellQuantity;

    /**
     * 系统仓-残次品库存
     */
    private BigDecimal systemWarehouseDefectiveQuantity;

    /**
     * 在途库存
     */
    private BigDecimal transportationQuantity;

    /**
     * 在途库存更新时间
     */
    private Date transportationQuantityUpdateTime;
}
