package com.rafael.acougue.service;

import com.rafael.acougue.dto.ProductDTO;
import java.util.List;

/**
 * Contrato base para operações com produtos.
 */
public interface ProductService {

    List<ProductDTO> list();
}
