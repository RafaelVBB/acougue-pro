package com.rafael.acougue.service.impl;

import com.rafael.acougue.domain.Product;
import com.rafael.acougue.domain.StockEvent;
import com.rafael.acougue.dto.StockEventDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.StockEventMapper;
import com.rafael.acougue.repository.ProductRepository;
import com.rafael.acougue.repository.StockEventRepository;
import com.rafael.acougue.service.StockService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    private final StockEventRepository stockEventRepository;
    private final ProductRepository productRepository;
    private final StockEventMapper stockEventMapper;

    public StockServiceImpl(StockEventRepository stockEventRepository,
                            ProductRepository productRepository,
                            StockEventMapper stockEventMapper) {
        this.stockEventRepository = stockEventRepository;
        this.productRepository = productRepository;
        this.stockEventMapper = stockEventMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StockEventDTO> findAll() {
        return stockEventRepository.findAll().stream().map(stockEventMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StockEventDTO findById(UUID id) {
        return stockEventMapper.toDto(getEvent(id));
    }

    @Override
    public StockEventDTO create(StockEventDTO dto) {
        StockEvent event = stockEventMapper.toEntity(dto);
        event.setId(null);
        event.setProduct(resolveProduct(dto.getProductId()));
        return stockEventMapper.toDto(stockEventRepository.save(event));
    }

    @Override
    public StockEventDTO update(UUID id, StockEventDTO dto) {
        StockEvent event = getEvent(id);
        if (dto.getProductId() != null && !dto.getProductId().equals(event.getProduct().getId())) {
            event.setProduct(resolveProduct(dto.getProductId()));
        }
        event.setDelta(dto.getDelta());
        event.setReason(dto.getReason());
        event.setCreatedAt(dto.getCreatedAt());
        return stockEventMapper.toDto(stockEventRepository.save(event));
    }

    @Override
    public void delete(UUID id) {
        stockEventRepository.delete(getEvent(id));
    }

    private StockEvent getEvent(UUID id) {
        return stockEventRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Evento de estoque não encontrado: " + id));
    }

    private Product resolveProduct(UUID id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Produto não encontrado: " + id));
    }
}
