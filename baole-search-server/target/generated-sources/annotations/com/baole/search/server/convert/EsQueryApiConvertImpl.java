package com.baole.search.server.convert;

import com.baole.search.domain.bo.es.EsGoodsInfoBO;
import com.baole.search.domain.bo.es.EsItemBO;
import com.baole.search.domain.bo.es.EsItemSkuBO;
import com.baole.search.dto.es.response.entity.EsGoodsResponseDTO;
import com.baole.search.dto.es.response.entity.EsItemResponseDTO;
import com.baole.search.dto.es.response.entity.EsItemSkuResponseDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
public class EsQueryApiConvertImpl implements EsQueryApiConvert {

    @Override
    public EsGoodsResponseDTO convertDTO(EsGoodsInfoBO esGoodsInfoBO) {
        if ( esGoodsInfoBO == null ) {
            return null;
        }

        EsGoodsResponseDTO esGoodsResponseDTO = new EsGoodsResponseDTO();

        esGoodsResponseDTO.setBosId( esGoodsInfoBO.getBosId() );
        esGoodsResponseDTO.setVid( esGoodsInfoBO.getVid() );
        esGoodsResponseDTO.setGoodsId( esGoodsInfoBO.getGoodsId() );
        esGoodsResponseDTO.setGoodsType( esGoodsInfoBO.getGoodsType() );
        esGoodsResponseDTO.setSubGoodsType( esGoodsInfoBO.getSubGoodsType() );
        esGoodsResponseDTO.setParentVid( esGoodsInfoBO.getParentVid() );
        esGoodsResponseDTO.setTitle( esGoodsInfoBO.getTitle() );
        esGoodsResponseDTO.setSubTitle( esGoodsInfoBO.getSubTitle() );
        esGoodsResponseDTO.setOuterGoodsId( esGoodsInfoBO.getOuterGoodsId() );
        esGoodsResponseDTO.setOuterGoodsCode( esGoodsInfoBO.getOuterGoodsCode() );
        List<Long> list = esGoodsInfoBO.getCategoryIdList();
        if ( list != null ) {
            esGoodsResponseDTO.setCategoryIdList( new ArrayList<Long>( list ) );
        }
        esGoodsResponseDTO.setBrandId( esGoodsInfoBO.getBrandId() );
        esGoodsResponseDTO.setSaleChannelType( esGoodsInfoBO.getSaleChannelType() );
        esGoodsResponseDTO.setSoldType( esGoodsInfoBO.getSoldType() );
        esGoodsResponseDTO.setGoodsCreateTime( esGoodsInfoBO.getGoodsCreateTime() );
        esGoodsResponseDTO.setGoodsUpdateTime( esGoodsInfoBO.getGoodsUpdateTime() );
        esGoodsResponseDTO.setIsCanSell( esGoodsInfoBO.getIsCanSell() );
        esGoodsResponseDTO.setIsAssigned( esGoodsInfoBO.getIsAssigned() );
        esGoodsResponseDTO.setIsOnline( esGoodsInfoBO.getIsOnline() );
        esGoodsResponseDTO.setOnlineDate( esGoodsInfoBO.getOnlineDate() );
        esGoodsResponseDTO.setSiteGoodsCreateTime( esGoodsInfoBO.getSiteGoodsCreateTime() );
        esGoodsResponseDTO.setSiteGoodsUpdateTime( esGoodsInfoBO.getSiteGoodsUpdateTime() );
        esGoodsResponseDTO.setCategoryId( esGoodsInfoBO.getCategoryId() );
        List<Long> list1 = esGoodsInfoBO.getClassifyIdList();
        if ( list1 != null ) {
            esGoodsResponseDTO.setClassifyIdList( new ArrayList<Long>( list1 ) );
        }
        List<Long> list2 = esGoodsInfoBO.getGoodsPropertyValueIdList();
        if ( list2 != null ) {
            esGoodsResponseDTO.setGoodsPropertyValueIdList( new ArrayList<Long>( list2 ) );
        }
        List<Long> list3 = esGoodsInfoBO.getPropertyIdList();
        if ( list3 != null ) {
            esGoodsResponseDTO.setPropertyIdList( new ArrayList<Long>( list3 ) );
        }

        return esGoodsResponseDTO;
    }

    @Override
    public EsItemResponseDTO convertDTO(EsItemBO esGoodsInfoBO) {
        if ( esGoodsInfoBO == null ) {
            return null;
        }

        EsItemResponseDTO esItemResponseDTO = new EsItemResponseDTO();

        esItemResponseDTO.setTcode( esGoodsInfoBO.getTcode() );
        esItemResponseDTO.setSourceProductId( esGoodsInfoBO.getSourceProductId() );
        esItemResponseDTO.setSourceProductVersionId( esGoodsInfoBO.getSourceProductVersionId() );
        esItemResponseDTO.setSourceProductInstanceId( esGoodsInfoBO.getSourceProductInstanceId() );
        esItemResponseDTO.setMerchantId( esGoodsInfoBO.getMerchantId() );
        esItemResponseDTO.setBosId( esGoodsInfoBO.getBosId() );
        esItemResponseDTO.setParentVid( esGoodsInfoBO.getParentVid() );
        esItemResponseDTO.setVid( esGoodsInfoBO.getVid() );
        esItemResponseDTO.setItemId( esGoodsInfoBO.getItemId() );
        esItemResponseDTO.setItemCode( esGoodsInfoBO.getItemCode() );
        esItemResponseDTO.setGoodsType( esGoodsInfoBO.getGoodsType() );
        esItemResponseDTO.setSubGoodsBizType( esGoodsInfoBO.getSubGoodsBizType() );
        esItemResponseDTO.setTitle( esGoodsInfoBO.getTitle() );
        esItemResponseDTO.setSubTitle( esGoodsInfoBO.getSubTitle() );
        esItemResponseDTO.setOuterGoodsId( esGoodsInfoBO.getOuterGoodsId() );
        esItemResponseDTO.setOuterGoodsCode( esGoodsInfoBO.getOuterGoodsCode() );
        esItemResponseDTO.setCategoryId( esGoodsInfoBO.getCategoryId() );
        List<Long> list = esGoodsInfoBO.getCategoryIdList();
        if ( list != null ) {
            esItemResponseDTO.setCategoryIdList( new ArrayList<Long>( list ) );
        }
        esItemResponseDTO.setBrandId( esGoodsInfoBO.getBrandId() );
        esItemResponseDTO.setIsCombine( esGoodsInfoBO.getIsCombine() );
        esItemResponseDTO.setReleaseStatus( esGoodsInfoBO.getReleaseStatus() );
        esItemResponseDTO.setItemCreateTime( esGoodsInfoBO.getItemCreateTime() );
        esItemResponseDTO.setItemUpdateTime( esGoodsInfoBO.getItemUpdateTime() );
        esItemResponseDTO.setIsDeleted( esGoodsInfoBO.getIsDeleted() );
        esItemResponseDTO.setIsAssigned( esGoodsInfoBO.getIsAssigned() );
        esItemResponseDTO.setIsOnline( esGoodsInfoBO.getIsOnline() );
        esItemResponseDTO.setType( esGoodsInfoBO.getType() );
        List<Long> list1 = esGoodsInfoBO.getClassifyIdList();
        if ( list1 != null ) {
            esItemResponseDTO.setClassifyIdList( new ArrayList<Long>( list1 ) );
        }
        List<Long> list2 = esGoodsInfoBO.getItemPropertyValueIdList();
        if ( list2 != null ) {
            esItemResponseDTO.setItemPropertyValueIdList( new ArrayList<Long>( list2 ) );
        }
        List<Long> list3 = esGoodsInfoBO.getPropertyIdList();
        if ( list3 != null ) {
            esItemResponseDTO.setPropertyIdList( new ArrayList<Long>( list3 ) );
        }
        esItemResponseDTO.setIsMultiSku( esGoodsInfoBO.getIsMultiSku() );
        esItemResponseDTO.setMinMarketPrice( esGoodsInfoBO.getMinMarketPrice() );
        esItemResponseDTO.setMinUnifiedPrice( esGoodsInfoBO.getMinUnifiedPrice() );
        esItemResponseDTO.setMinCostPrice( esGoodsInfoBO.getMinCostPrice() );
        esItemResponseDTO.setMaxMarketPrice( esGoodsInfoBO.getMaxMarketPrice() );
        esItemResponseDTO.setMaxUnifiedPrice( esGoodsInfoBO.getMaxUnifiedPrice() );
        esItemResponseDTO.setMaxCostPrice( esGoodsInfoBO.getMaxCostPrice() );
        esItemResponseDTO.setSort( esGoodsInfoBO.getSort() );
        esItemResponseDTO.setIsCurrentVidCreate( esGoodsInfoBO.getIsCurrentVidCreate() );
        esItemResponseDTO.setSystemWarehouseSellQuantity( esGoodsInfoBO.getSystemWarehouseSellQuantity() );
        esItemResponseDTO.setSystemWarehouseDefectiveQuantity( esGoodsInfoBO.getSystemWarehouseDefectiveQuantity() );

        return esItemResponseDTO;
    }

    @Override
    public EsItemSkuResponseDTO convertDTO(EsItemSkuBO esGoodsInfoBO) {
        if ( esGoodsInfoBO == null ) {
            return null;
        }

        EsItemSkuResponseDTO esItemSkuResponseDTO = new EsItemSkuResponseDTO();

        esItemSkuResponseDTO.setBosId( esGoodsInfoBO.getBosId() );
        esItemSkuResponseDTO.setParentVid( esGoodsInfoBO.getParentVid() );
        esItemSkuResponseDTO.setVid( esGoodsInfoBO.getVid() );
        esItemSkuResponseDTO.setItemId( esGoodsInfoBO.getItemId() );
        esItemSkuResponseDTO.setSkuId( esGoodsInfoBO.getSkuId() );
        esItemSkuResponseDTO.setCategoryId( esGoodsInfoBO.getCategoryId() );
        esItemSkuResponseDTO.setBrandId( esGoodsInfoBO.getBrandId() );
        List<Long> list = esGoodsInfoBO.getClassifyIdList();
        if ( list != null ) {
            esItemSkuResponseDTO.setClassifyIdList( new ArrayList<Long>( list ) );
        }
        esItemSkuResponseDTO.setIsCombine( esGoodsInfoBO.getIsCombine() );
        esItemSkuResponseDTO.setReleaseStatus( esGoodsInfoBO.getReleaseStatus() );
        esItemSkuResponseDTO.setItemCreateTime( esGoodsInfoBO.getItemCreateTime() );
        esItemSkuResponseDTO.setItemUpdateTime( esGoodsInfoBO.getItemUpdateTime() );
        esItemSkuResponseDTO.setItemTitle( esGoodsInfoBO.getItemTitle() );
        esItemSkuResponseDTO.setGoodsType( esGoodsInfoBO.getGoodsType() );
        esItemSkuResponseDTO.setOuterGoodsId( esGoodsInfoBO.getOuterGoodsId() );
        esItemSkuResponseDTO.setOuterGoodsCode( esGoodsInfoBO.getOuterGoodsCode() );
        esItemSkuResponseDTO.setSkuBarCode( esGoodsInfoBO.getSkuBarCode() );
        esItemSkuResponseDTO.setOuterSkuCode( esGoodsInfoBO.getOuterSkuCode() );
        esItemSkuResponseDTO.setSkuTitle( esGoodsInfoBO.getSkuTitle() );
        esItemSkuResponseDTO.setIsDisabled( esGoodsInfoBO.getIsDisabled() );
        esItemSkuResponseDTO.setIsDeleted( esGoodsInfoBO.getIsDeleted() );
        esItemSkuResponseDTO.setUnifiedPrice( esGoodsInfoBO.getUnifiedPrice() );
        esItemSkuResponseDTO.setMarketPrice( esGoodsInfoBO.getMarketPrice() );
        esItemSkuResponseDTO.setCostPrice( esGoodsInfoBO.getCostPrice() );
        esItemSkuResponseDTO.setSkuCreateTime( esGoodsInfoBO.getSkuCreateTime() );
        esItemSkuResponseDTO.setSkuUpdateTime( esGoodsInfoBO.getSkuUpdateTime() );

        return esItemSkuResponseDTO;
    }
}
