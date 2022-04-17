package com.baole.search.domain.bo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 基础数据 do
 */
@Getter
@Setter
public class BaseDataBO implements Serializable {

    private static final long serialVersionUID = -8303665580889695914L;

    /**
     * 主键 id
     */
    private Long id;

    /**
     * 租户编码
     */
    private String tcode;

    /**
     * 产品 id
     */
    private Long sourceProductId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;


}
