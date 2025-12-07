package com.rafael.acougue.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class SaleItemDTO {

    private UUID id;
    private UUID saleId;
    private ProductDTO product;
    private BigDecimal qty;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
}
