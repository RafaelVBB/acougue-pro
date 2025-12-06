package com.rafael.acougue.service.impl;

import com.rafael.acougue.dto.ProductDTO;
import com.rafael.acougue.service.ProductService;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductDTO> list() {
        return Collections.emptyList();
    }
}
