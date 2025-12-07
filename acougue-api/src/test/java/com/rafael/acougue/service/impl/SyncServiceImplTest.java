package com.rafael.acougue.service.impl;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rafael.acougue.repository.SaleRepository;
import com.rafael.acougue.repository.TerminalRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SyncServiceImplTest {

    @Mock
    private TerminalRepository terminalRepository;

    @Mock
    private SaleRepository saleRepository;

    @InjectMocks
    private SyncServiceImpl syncService;

    @Test
    void executeSyncShouldQueryRepositories() {
        when(terminalRepository.count()).thenReturn(2L);
        when(saleRepository.count()).thenReturn(5L);

        syncService.executeSync();

        verify(terminalRepository, times(1)).count();
        verify(saleRepository, times(1)).count();
    }
}
