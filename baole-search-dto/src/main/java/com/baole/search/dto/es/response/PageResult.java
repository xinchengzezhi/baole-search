package com.baole.search.dto.es.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = 2124371130987381653L;

    private int pageNum;

    private int pageSize;

    private int totalCount;

    private String searchAfter;

    private List<T> details;

}
