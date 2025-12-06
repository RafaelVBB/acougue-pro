package com.rafael.acougue.service.impl;

import com.rafael.acougue.dto.ProdutoDTO;
import com.rafael.acougue.service.ProdutoService;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Override
    public List<ProdutoDTO> listar() {
        return Collections.emptyList();
    }
}
