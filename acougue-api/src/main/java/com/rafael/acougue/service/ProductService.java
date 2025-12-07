package com.rafael.acougue.service;

import com.rafael.acougue.dto.ProductDTO;
import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<ProductDTO> findAll();

    ProductDTO findById(UUID id);

    ProductDTO create(ProductDTO dto);

    ProductDTO update(UUID id, ProductDTO dto);

    void delete(UUID id);
}
