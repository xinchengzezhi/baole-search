package com.baole.search.dto.es.response;

import com.baole.search.dto.es.enums.EsAggregationEnum;
import com.baole.search.dto.es.request.EsAggregationRequestDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * es 聚合查询结果
 */
@Getter
@Setter
@ToString
public class EsAggregationResponseDTO implements Serializable {

    private static final long serialVersionUID = 8053751506644183411L;

    /**
     * 聚合查询命名
     */
    private String name;

    /**
     * 聚合类型
     *
     * @see EsAggregationEnum
     */
    private int aggregationType;

    /**
     * 查询结果，map key 与 aggregationType 绑定
     *
     * @see com.baole.search.dto.es.enums.agg
     */
    private List<Map<String, String>> fieldAndValueList;

    /**
     * @see EsAggregationRequestDTO#getSelectTotalCount() count
     * <p>
     * 剩余未被查询的数量 sumOtherCount = total - count
     */
    private Long sumOtherCount;
}
