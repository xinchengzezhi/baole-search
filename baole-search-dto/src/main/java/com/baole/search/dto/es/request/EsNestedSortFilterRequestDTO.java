package com.baole.search.dto.es.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 查询实体
 */
@Getter
@Setter
@ToString
public class EsNestedSortFilterRequestDTO implements Serializable {

    private static final long serialVersionUID = 8651311309617926942L;

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
     * 查询值
     */
    private Object value;

}