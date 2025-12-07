package com.rafael.acougue.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class StockEventDTO {

    private UUID id;
    private UUID productId;
    private BigDecimal delta;
    private String reason;
    private Instant createdAt;
}
