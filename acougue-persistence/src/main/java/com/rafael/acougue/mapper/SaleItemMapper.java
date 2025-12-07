package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.SaleItem;
import com.rafael.acougue.dto.SaleItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {ProductMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleItemMapper {

    @Mapping(source = "sale.id", target = "saleId")
    SaleItemDTO toDto(SaleItem entity);

    @Mapping(target = "sale", ignore = true)
    SaleItem toEntity(SaleItemDTO dto);
}
