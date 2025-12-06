package com.rafael.acougue.repository;

import com.rafael.acougue.domain.Produto;
import java.util.Optional;
import java.util.UUID;

/**
 * Contrato base para repositórios de produto.
 */
public interface ProdutoRepository {

    Optional<Produto> findById(UUID id);
}
