package com.rafael.acougue.service;

import com.rafael.acougue.dto.ProdutoDTO;
import java.util.List;

/**
 * Contrato base para operações com produtos.
 */
public interface ProdutoService {

    List<ProdutoDTO> listar();
}
