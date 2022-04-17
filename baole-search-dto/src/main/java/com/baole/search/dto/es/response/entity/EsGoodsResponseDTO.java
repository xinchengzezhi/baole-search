package com.baole.search.dto.es.response.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * es goods 查询结果
 */
@Getter
@Setter
@ToString
public class EsGoodsResponseDTO extends EsExtResponseDTO {

    private static final long serialVersionUID = -932254925789108163L;

    /**
     * 组织架构树 id
     * <p>
     * 新定义
     */
    private Long bosId;

    /**
     * 树节点 id
     * <p>
     * 新定义
     */
    private Long vid;

    /**
     * 商品 id
     * <p>
     * ec-goods: goodsId
     */
    private Long goodsId;

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
     * 创建者的组织 id
     * <p>
     * ec-goods: createStoreId
     */
    private Long parentVid;

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
    private String outerGoodsId;

    /**
     * 外部商品 code
     * <p>
     * ec-goods: outerGoodsCode
     */
    private String outerGoodsCode;

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

    /**
     * 销售渠道类型
     * <p>
     * ec-goods: saleChannelType
     */
    private Integer saleChannelType;

    /**
     * 售卖类型
     * <p>
     * ec-goods: sellModelType
     */
    private Integer soldType;

    /**
     * 商品创建时间
     * <p>
     * ec-goods: goodsCreateTime
     */
    private Date goodsCreateTime;

    /**
     * 商品更新时间
     * <p>
     * ec-goods: goodsUpdateTime
     */
    private Date goodsUpdateTime;

    /**
     * 是否可售卖
     * <p>
     * ec-goods: isCanSell
     */
    private Boolean isCanSell;

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
     * 上架时间
     * <p>
     * ec-goods: putAwayDate
     */
    private Date onlineDate;

    /**
     * 场所商品创建时间
     * <p>
     * ec-goods: storeGoodsCreateTime
     */
    private Date siteGoodsCreateTime;

    /**
     * 场所商品更新时间
     * <p>
     * ec-goods: storeGoodsUpdateTime
     */
    private Date siteGoodsUpdateTime;

    /**
     * 商品归属的类目 id
     */
    private Long categoryId;

    /**
     * 前台分组 id list
     * <p>
     * ec-goods: classifyIdList
     */
    private List<Long> classifyIdList;

    /**
     * 商品可检索的属性值 id list
     * <p>
     * ec-goods: goodsPropertyValueIdList, goodsInnerPropertyValueIdList, goodsRightsValueIdList
     */
    private List<Long> goodsPropertyValueIdList;

    /**
     * 商品可检索属性 id list
     * <p>
     * ec-goods: goodsRightsIdList
     */
    private List<Long> propertyIdList;


}
