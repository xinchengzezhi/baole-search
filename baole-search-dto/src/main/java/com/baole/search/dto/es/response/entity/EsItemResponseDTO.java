package com.baole.search.dto.es.response.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class EsItemResponseDTO extends EsExtResponseDTO  implements Serializable {
    private static final long serialVersionUID = -1147251011922192327L;
    /**
     * 租户 code
     * <p>
     * 新定义
     */
    private String tcode;

    /**
     * 产品 id
     * <p>
     * 新定义
     */
    private Long sourceProductId;

    /**
     * 产品版本
     * <p>
     * 新定义
     */
    private Long sourceProductVersionId;

    /**
     * 产品实例 id
     * <p>
     * 新定义
     */
    private Long sourceProductInstanceId;

    /**
     * 商户 id
     * <p>
     * 新定义
     */
    private Long merchantId;

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
     * 后台商品 id
     * <p>
     * ec-goods: itemId
     */
    private Long itemId;

    /**
     * 商品编码
     */
    private String itemCode;

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
    private Integer subGoodsBizType;

    /**
     * 商品标题
     * <p>
     * ec-goods: title
     */
    private String title;

    /**
     * 商品副标题
     */
    private String subTitle;

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
     * 商品归属的类目 id
     */
    private Long categoryId;

    /**
     * 全部类目 id list
     * <p>
     * ec-goods: categoryIdList
     */
    private List<Long> categoryIdList;

    /**
     * 品牌 id
     */
    private Long brandId;

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
     * 是否被删除
     * <p>
     * ec-goods: isDeleted
     */
    private Boolean isDeleted;

    /**
     * 分配状态
     * <p>
     * ec-goods: isAssigned
     */
    private Boolean isAssigned;

    /**
     * 是否上架
     * <p>
     * ec-goods: isPutAway
     */
    private Boolean isOnline;

    /**
     * 1分配、2引用、3自建
     */
    private Integer type;

    /**
     * 场所商品创建时间
     * <p>
     * ec-goods: storeGoodsCreateTime
     */
    private Date siteItemCreateTime;

    /**
     * 场所商品更新时间
     * <p>
     * ec-goods: storeGoodsUpdateTime
     */
    private Date siteItemUpdateTime;

    /**
     * 后台分组 id list
     * <p>
     * ec-goods: classifyIdList
     */
    private List<Long> classifyIdList;

    /**
     * 商品可检索的属性值 id list
     * <p>
     * ec-goods: goodsPropertyValueIdList, goodsInnerPropertyValueIdList, goodsRightsValueIdList
     */
    private List<Long> itemPropertyValueIdList;

    /**
     * 商品可检索属性 id list
     * <p>
     * ec-goods: goodsRightsIdList
     */
    private List<Long> propertyIdList;


    /**
     * 判断商品是否多规格
     * <p>
     * ec-goods: isMultiSku
     */
    private Boolean isMultiSku;

    /** 市场价**/
    private BigDecimal minMarketPrice;

    /** 统一价**/
    private BigDecimal minUnifiedPrice;

    /** 成本价**/
    private BigDecimal minCostPrice;

    /** 市场价**/
    private BigDecimal maxMarketPrice;

    /** 统一价**/
    private BigDecimal maxUnifiedPrice;

    /** 成本价**/
    private BigDecimal maxCostPrice;

    /**
     * 基础商品排序
     * <p>
     * ec-goods: sort
     */
    private Integer sort;

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


}
