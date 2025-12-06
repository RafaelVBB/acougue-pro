package com.rafael.acougue.sync;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncScheduler {

    @Scheduled(fixedDelayString = "PT5M")
    public void sincronizarPlaceholder() {
        // Execução placeholder para rotinas de sincronização
    }
}
