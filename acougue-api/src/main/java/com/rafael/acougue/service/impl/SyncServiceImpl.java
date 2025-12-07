package com.rafael.acougue.service.impl;

import com.rafael.acougue.repository.SaleRepository;
import com.rafael.acougue.repository.TerminalRepository;
import com.rafael.acougue.service.SyncService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SyncServiceImpl implements SyncService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SyncServiceImpl.class);

    private final TerminalRepository terminalRepository;
    private final SaleRepository saleRepository;

    public SyncServiceImpl(TerminalRepository terminalRepository, SaleRepository saleRepository) {
        this.terminalRepository = terminalRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void executeSync() {
        long terminalCount = terminalRepository.count();
        long pendingSales = saleRepository.count();
        LOGGER.info("Executando sincronização. terminais={}, vendas={}.", terminalCount, pendingSales);
    }
}
