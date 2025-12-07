package com.rafael.acougue.service.impl;

import com.rafael.acougue.domain.Product;
import com.rafael.acougue.dto.ProductDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.ProductMapper;
import com.rafael.acougue.repository.ProductRepository;
import com.rafael.acougue.service.ProductService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(productMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductDTO findById(UUID id) {
        return productMapper.toDto(getProduct(id));
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product product = productMapper.toEntity(dto);
        product.setId(null);
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public ProductDTO update(UUID id, ProductDTO dto) {
        Product product = getProduct(id);
        product.setSku(dto.getSku());
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setUnit(dto.getUnit());
        product.setStockQuantity(dto.getStockQuantity());
        product.setLastModifiedAt(dto.getLastModifiedAt());
        product.setActive(dto.isActive());
        return productMapper.toDto(productRepository.save(product));
    }

    @Override
    public void delete(UUID id) {
        productRepository.delete(getProduct(id));
    }

    private Product getProduct(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Produto não encontrado: " + id));
    }
}
