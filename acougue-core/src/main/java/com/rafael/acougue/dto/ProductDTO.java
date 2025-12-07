package com.rafael.acougue.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;
import lombok.Data;

@Data
public class ProductDTO {

    private UUID id;
    private String sku;
    private String name;
    private BigDecimal price;
    private String unit;
    private BigDecimal stockQuantity;
    private Instant lastModifiedAt;
    private boolean active;
}
