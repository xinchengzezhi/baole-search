package com.baole.search.dto.es.request;

import com.baole.search.dto.es.enums.EsRangeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 范围查询实体
 */
@Getter
@Setter
@ToString
public class EsRangeRequestDTO implements Serializable {

    private static final long serialVersionUID = -2348120758255575877L;

    /**
     * 大于
     */
    private Object from;

    /**
     * @see EsRangeEnum
     */
    private int fromType = EsRangeEnum.NORMAL.getRangeType();

    /**
     * 小于
     */
    private Object to;

    /**
     * @see EsRangeEnum
     */
    private int toType = EsRangeEnum.NORMAL.getRangeType();
}
