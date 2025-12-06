package com.rafael.acougue.mapper;

import com.rafael.acougue.domain.Produto;
import com.rafael.acougue.dto.ProdutoDTO;
import org.mapstruct.Mapper;

/**
 * Mapper base para conversão entre entidades e DTOs de produto.
 */
@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO toDto(Produto produto);

    Produto toEntity(ProdutoDTO dto);
}
