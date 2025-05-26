package com.itesm.fennec.infrastructure.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class InvestmentResponse {
    private Long id;
    private Long propertyId;
    private String propertyTitle;
    private BigDecimal amountInvested;
    private BigDecimal participationPercentage;
    private LocalDate investmentDate;
}