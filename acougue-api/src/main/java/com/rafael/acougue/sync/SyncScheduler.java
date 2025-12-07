package com.rafael.acougue.sync;

import com.rafael.acougue.service.SyncService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SyncScheduler {

    private final SyncService syncService;

    public SyncScheduler(SyncService syncService) {
        this.syncService = syncService;
    }

    @Scheduled(fixedDelayString = "PT5M")
    public void sincronizar() {
        syncService.executeSync();
    }
}
