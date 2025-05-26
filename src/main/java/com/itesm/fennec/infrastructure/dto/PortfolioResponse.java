package com.itesm.fennec.infrastructure.dto;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class PortfolioResponse {
    private Integer userId;
    private BigDecimal totalInvested;
    private int propertyCount;
    private List<InvestmentResponse> investments;
}