package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.StockEvent;
import com.rafael.acougue.dto.StockEventDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StockEventMapper {

    @Mapping(source = "product.id", target = "productId")
    StockEventDTO toDto(StockEvent entity);

    @Mapping(target = "product", ignore = true)
    StockEvent toEntity(StockEventDTO dto);
}
