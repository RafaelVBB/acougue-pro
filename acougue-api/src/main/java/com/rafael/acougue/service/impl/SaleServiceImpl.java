package com.rafael.acougue.service.impl;

import com.rafael.acougue.domain.Sale;
import com.rafael.acougue.dto.SaleDTO;
import com.rafael.acougue.exception.NotFoundException;
import com.rafael.acougue.mapper.SaleMapper;
import com.rafael.acougue.repository.SaleRepository;
import com.rafael.acougue.service.SaleService;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SaleMapper saleMapper) {
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SaleDTO> findAll() {
        return saleRepository.findAll().stream().map(saleMapper::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SaleDTO findById(UUID id) {
        return saleMapper.toDto(getSale(id));
    }

    @Override
    public SaleDTO create(SaleDTO dto) {
        Sale sale = saleMapper.toEntity(dto);
        sale.setId(null);
        return saleMapper.toDto(saleRepository.save(sale));
    }

    @Override
    public SaleDTO update(UUID id, SaleDTO dto) {
        Sale existing = getSale(id);
        Sale incoming = saleMapper.toEntity(dto);
        mergeSale(existing, incoming);
        return saleMapper.toDto(saleRepository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        saleRepository.delete(getSale(id));
    }

    private Sale getSale(UUID id) {
        return saleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Venda não encontrada: " + id));
    }

    private void mergeSale(Sale target, Sale source) {
        target.setExternalId(source.getExternalId());
        target.setTerminalId(source.getTerminalId());
        target.setTotal(source.getTotal());
        target.setStatus(source.getStatus());
        target.setCreatedAt(source.getCreatedAt());
        target.setConfirmedAt(source.getConfirmedAt());

        target.getItems().clear();
        if (source.getItems() != null) {
            source.getItems().forEach(item -> {
                item.setSale(target);
                target.getItems().add(item);
            });
        }
    }
}
