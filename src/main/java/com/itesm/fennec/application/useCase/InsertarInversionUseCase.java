package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.InvestmentService;
import com.itesm.fennec.domain.model.Investment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InsertarInversionUseCase {
    @Inject
    InvestmentService investmentService;
    public Investment execute(Investment investment) {
        investment = investmentService.insetarInvestment(investment);
        return investment;
    }
}
