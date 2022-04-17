package com.baole.search.dto.es.request;

import com.baole.search.dto.es.enums.EsSortEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 排序查询
 */
@Getter
@Setter
@ToString
public class EsSortRequestDTO implements Serializable {

    private static final long serialVersionUID = 388511951469807819L;

    /**
     * 排序字段名
     *
     * @see com.baole.search.dto.es.enums.index
     */
    private String sortFieldName;

    /**
     * 嵌套排序 path
     */
    private String nestedSortPath;

    /**
     * 嵌套排序，排序过滤器
     */
    private EsNestedSortFilterRequestDTO esNestedSortFilterRequestDTO;

    /**
     * 排序类型
     *
     * @see EsSortEnum
     */
    private int sortType = EsSortEnum.ASC.getSortType();
}
