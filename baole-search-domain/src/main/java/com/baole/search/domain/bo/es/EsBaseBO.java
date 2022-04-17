package com.baole.search.domain.bo.es;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class EsBaseBO implements Serializable {

    private static final long serialVersionUID = 1221136200053531189L;

    private String id;

    private String tcode;

    private Long sourceProductId;

    private Long sourceProductInstanceId;

    private Long merchantId;

    private Date esCreateTime;

    private Date esUpdateTime;

    private Boolean isDeleted;
}
