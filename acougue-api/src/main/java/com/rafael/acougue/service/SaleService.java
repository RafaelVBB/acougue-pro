package com.rafael.acougue.service;

import com.rafael.acougue.dto.SaleDTO;
import java.util.List;
import java.util.UUID;

public interface SaleService {

    List<SaleDTO> findAll();

    SaleDTO findById(UUID id);

    SaleDTO create(SaleDTO dto);

    SaleDTO update(UUID id, SaleDTO dto);

    void delete(UUID id);
}
