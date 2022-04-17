package com.baole.search.server.convert;

import com.baole.search.domain.bo.es.*;
import com.baole.search.dto.es.response.entity.*;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EsQueryApiConvert {

    EsQueryApiConvert INSTANCE = Mappers.getMapper(EsQueryApiConvert.class);

    @Mapping(target = "extField", ignore = true)
    EsGoodsResponseDTO convertDTO(EsGoodsInfoBO esGoodsInfoBO);

    EsItemResponseDTO convertDTO(EsItemBO esGoodsInfoBO);

    EsItemSkuResponseDTO convertDTO(EsItemSkuBO esGoodsInfoBO);
}
