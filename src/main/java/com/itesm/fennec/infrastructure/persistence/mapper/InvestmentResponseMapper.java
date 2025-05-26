package com.itesm.fennec.infrastructure.persistence.mapper;


import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.infrastructure.dto.InvestmentResponse;

import java.util.List;
import java.util.stream.Collectors;

public class InvestmentResponseMapper {

    public static InvestmentResponse toResponse(Investment investment) {
        InvestmentResponse dto = new InvestmentResponse();
        dto.setId(investment.getId());
        dto.setPropertyId(investment.getProperty().getId());
        dto.setPropertyTitle(investment.getProperty().getTitle()); // opcional
        dto.setAmountInvested(investment.getAmountInvested());
        dto.setParticipationPercentage(investment.getParticipationPercentage());
        dto.setInvestmentDate(investment.getInvestmentDate());
        return dto;
    }

    public static List<InvestmentResponse> toResponseList(List<Investment> investments) {
        return investments.stream().map(InvestmentResponseMapper::toResponse).collect(Collectors.toList());
    }
}