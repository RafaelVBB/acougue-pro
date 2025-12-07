package com.rafael.acougue.service;

import com.rafael.acougue.dto.StockEventDTO;
import java.util.List;
import java.util.UUID;

public interface StockService {

    List<StockEventDTO> findAll();

    StockEventDTO findById(UUID id);

    StockEventDTO create(StockEventDTO dto);

    StockEventDTO update(UUID id, StockEventDTO dto);

    void delete(UUID id);
}
