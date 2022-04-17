package com.baole.search.dto.es.request;

import com.baole.search.dto.es.enums.EsLinkEnum;
import com.baole.search.dto.es.enums.EsQueryEnum;
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
public class EsEntityRequestDTO implements Serializable {

    private static final long serialVersionUID = -2360020176989605451L;

    /**
     * 查询字段名
     *
     * @see com.baole.search.dto.es.enums.index
     */
    private String fieldName;

    /**
     * 查询值
     */
    private List<Object> values;

    /**
     * 查询类型
     *
     * @see EsQueryEnum
     */
    private int queryType = EsQueryEnum.TERM.getQueryType();

    /**
     * 内嵌字段名
     * <p>
     *
     * @see EsQueryEnum#NESTED queryType
     */
    private String nestedPath;

    /**
     * es 范围查询
     * <p>
     *
     * @see EsQueryEnum#RANGE queryType
     */
    private EsRangeRequestDTO esRangeRequestDTO;

    /**
     * 内联查询模型拼接
     *
     * @see EsLinkEnum
     */
    private int innerRequestType = EsLinkEnum.AND.getLinkType();

    /**
     * 内联查询实体 list
     */
    private List<EsEntityRequestDTO> innerRequestList;
}