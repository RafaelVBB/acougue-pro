package com.rafael.acougue.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rafael.acougue.domain.Product;
import com.rafael.acougue.dto.ProductDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.ProductMapper;
import com.rafael.acougue.repository.ProductRepository;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void findByIdShouldReturnDtoWhenFound() {
        UUID id = UUID.randomUUID();
        Product entity = new Product();
        entity.setId(id);
        ProductDTO dto = new ProductDTO();
        dto.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(entity));
        when(productMapper.toDto(entity)).thenReturn(dto);

        ProductDTO result = productService.findById(id);

        assertThat(result).isEqualTo(dto);
        verify(productRepository).findById(id);
    }

    @Test
    void findByIdShouldThrowWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productService.findById(id));
        verify(productRepository).findById(id);
    }
}
