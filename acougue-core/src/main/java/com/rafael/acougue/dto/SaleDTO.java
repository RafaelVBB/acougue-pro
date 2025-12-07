package com.rafael.acougue.dto;

import com.rafael.acougue.domain.enums.SaleStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Data;

@Data
public class SaleDTO {

    private UUID id;
    private String externalId;
    private UUID terminalId;
    private BigDecimal total;
    private SaleStatus status;
    private Instant createdAt;
    private Instant confirmedAt;
    private List<SaleItemDTO> items = new ArrayList<>();
}
