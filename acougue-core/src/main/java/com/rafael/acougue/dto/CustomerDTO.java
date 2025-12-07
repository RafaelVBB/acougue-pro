package com.rafael.acougue.dto;

import java.math.BigDecimal;
import java.util.UUID;
import lombok.Data;

@Data
public class CustomerDTO {

    private UUID id;
    private String name;
    private String cpfCnpj;
    private BigDecimal creditLimit;
    private BigDecimal outstandingBalance;
    private boolean active;
}
