package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.Product;
import com.rafael.acougue.dto.ProductDTO;
import org.mapstruct.Mapper;

/**
 * Mapper para conversão entre entidades e DTOs de produto.
 */
@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDto(Product product);

    Product toEntity(ProductDTO dto);
}
