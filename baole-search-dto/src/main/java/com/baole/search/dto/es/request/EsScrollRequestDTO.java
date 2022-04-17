package com.baole.search.dto.es.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 滚动查询实体
 */
@Getter
@Setter
@ToString
public class EsScrollRequestDTO implements Serializable {

    private static final long serialVersionUID = 4367158563546711406L;

    /**
     * 滚动游标生存时间
     */
    private long scrollTimeInMillis;

    /**
     * 滚动游标，首次调用无需传入
     */
    private String scrollId = null;

    /**
     * 是否最后一次调用，为 true 时删除游标
     */
    private boolean finish = false;
}
