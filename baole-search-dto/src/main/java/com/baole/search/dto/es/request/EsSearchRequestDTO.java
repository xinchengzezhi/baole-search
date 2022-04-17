package com.baole.search.dto.es.request;

import com.baole.search.dto.es.enums.EsIndexEnum;
import com.baole.search.dto.es.response.entity.EsExtResponseDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * es 查询
 */
@Getter
@Setter
@ToString
public class EsSearchRequestDTO implements Serializable {
    private static final long serialVersionUID = -2631240278366871099L;

    /**
     * 租户 code
     */
    private String tcode;

    /**
     * 产品 id
     */
    private Long sourceProductId;

    /**
     * es 索引
     *
     * @see EsIndexEnum
     */
    private Integer indexCode;

    /**
     * 页码 >= 1
     */
    private Integer page = 1;

    /**
     * 页大小 > 0
     */
    private Integer size = 10;

    /**
     * 默认不返回扩展信息
     * <p>
     * 为 true 时，返回扩展信息 {@link EsExtResponseDTO}
     */
    private boolean supplementaryExtInfo = false;

    /**
     * 默认不查询已被删除的数据
     * <p>
     * 为 true 时，不进行约束，所有数据均能查询
     */
    private boolean delete = false;

    /**
     * 排序查询
     */
    private List<EsSortRequestDTO> sortRequestDTOList;

    /**
     * 聚合查询 list
     */
    private List<EsAggregationRequestDTO> aggregationRequestDTOList;

    /**
     * 是否使用滚动查询，携带 scrollId 时，无需再次构造 selectForAndList 查询模型
     */
    private EsScrollRequestDTO esScrollRequestDTO;

    /**
     * searchAfter 根据排序字段生成，第一次无需传递，第二次将上次返回的searchAfter数值返回即可
     */
    private String searchAfter;

    /**
     * 是否将扩展字段转为具体的业务字段。 false不转，默认会转
     */
    private Boolean isChangeExtField = true;

    public int getEsPage() {
        return page - 1;
    }












    private List<String> includes;

    private Boolean trackTotalHits;

    private Long sourceProductInstanceId;

    private Long merchantId;

    private Long bosId;

    private Long vid;

    private Boolean isCanSell;

    private Boolean isOnline;

    private List<Long> channelIds;

    private Boolean isAssigned;

    private List<Long> tagCodeList;

    private Integer from;

    private String sortColumn;

    private String sort;

}
