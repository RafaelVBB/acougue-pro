package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.Sale;
import com.rafael.acougue.dto.SaleDTO;
import java.util.List;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {SaleItemMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper {

    SaleDTO toDto(Sale sale);

    Sale toEntity(SaleDTO dto);

    List<SaleDTO> toDto(List<Sale> sales);

    List<Sale> toEntity(List<SaleDTO> dtos);

    @AfterMapping
    default void linkItems(@MappingTarget Sale sale) {
        if (sale.getItems() == null) {
            return;
        }
        sale.getItems().forEach(item -> item.setSale(sale));
    }
}
