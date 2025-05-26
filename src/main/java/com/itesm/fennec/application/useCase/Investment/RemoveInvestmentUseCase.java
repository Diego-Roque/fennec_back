package com.itesm.fennec.application.useCase.Investment;

import com.itesm.fennec.application.service.InvestmentService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;
@ApplicationScoped
@RequiredArgsConstructor
public class RemoveInvestmentUseCase {

    private final InvestmentService investmentService;

    public void execute(Long investmentId) {
        investmentService.deleteInvestment(investmentId);
    }
}

