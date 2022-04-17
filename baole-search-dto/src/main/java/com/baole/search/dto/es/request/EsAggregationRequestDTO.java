package com.baole.search.dto.es.request;

import com.baole.search.dto.es.enums.EsAggregationEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 聚合查询
 */
@Getter
@Setter
@ToString
public class EsAggregationRequestDTO implements Serializable {

    private static final long serialVersionUID = -4948325525180743872L;

    /**
     * 聚合查询命名
     */
    private String name;

    /**
     * 查询字段名
     *
     * @see com.baole.search.dto.es.enums.index
     */
    private String fieldName;

    /**
     * 是否为扩展字段，true 为扩展字段
     */
    private boolean isExtField = false;

    /**
     * 聚合类型
     *
     * @see EsAggregationEnum
     */
    private int aggregationType = EsAggregationEnum.DISTINCT.getAggregationType();

    /**
     * 查询总数，分页是查询总数下的分页
     */
    private int selectTotalCount = 10000;

    /**
     * 页码 >= 1
     */
    private int page = 1;

    /**
     * 页大小 > 0
     */
    private int size = 10;

    public int getFrom() {
        return (page - 1) * size;
    }
}
