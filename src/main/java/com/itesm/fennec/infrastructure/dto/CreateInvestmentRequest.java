package com.itesm.fennec.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateInvestmentRequest {
    private Long propertyId;
    private Integer userId;
    private BigDecimal amountInvested;
    private BigDecimal participationPercentage; // opcional
}
