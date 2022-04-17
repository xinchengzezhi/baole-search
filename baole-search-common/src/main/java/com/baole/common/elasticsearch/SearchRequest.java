/*
 * qccr.com Inc.
 * Copyright (c) 2014-2019 All Rights Reserved.
 */

package com.baole.common.elasticsearch;

import com.baole.search.dto.es.request.EsScrollRequestDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Description:
 * @Author: baole.liang
 * @Date: 2021/9/29
 */
@Getter
@Setter
@NoArgsConstructor
public class SearchRequest {

    private String index;

    private String dsl;

    /**
     * 是否使用滚动查询，携带 scrollId 时，无需再次构造 selectForAndList 查询模型
     */
    private EsScrollRequestDTO esScrollRequestDTO;

    public SearchRequest(String index, String dsl) {
        this.index = index;
        this.dsl = dsl;
    }

    public SearchRequest(String index, String dsl, EsScrollRequestDTO esScrollRequestDTO) {
        this.index = index;
        this.dsl = dsl;
        this.esScrollRequestDTO = esScrollRequestDTO;
    }
}
