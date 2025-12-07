package com.rafael.acougue.dto;

import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class TerminalDTO {

    private UUID id;
    private String name;
    private String token;
    private Instant lastSyncAt;
    private boolean active;
}
