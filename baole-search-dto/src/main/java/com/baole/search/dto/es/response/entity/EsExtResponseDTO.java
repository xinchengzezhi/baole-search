package com.baole.search.dto.es.response.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
public class EsExtResponseDTO {

    private static final long serialVersionUID = -5688763136583350604L;

    /**
     * 扩展字段，< 字段名, 字段值 >
     */
    private Map<String, String> extField;
}
