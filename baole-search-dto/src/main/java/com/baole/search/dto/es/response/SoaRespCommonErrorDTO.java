package com.baole.search.dto.es.response;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

@Data
public class SoaRespCommonErrorDTO implements Serializable {
    private static final long serialVersionUID = 7369048386509330983L;
    private java.lang.String errMsg;
    private java.lang.String errCode;
}
