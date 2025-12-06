package com.rafael.acougue.repository;

import com.rafael.acougue.domain.Product;
import java.util.Optional;
import java.util.UUID;

/**
 * Contrato base para operações com produtos.
 */
public interface ProductRepository {

    Optional<Product> findById(UUID id);
}
