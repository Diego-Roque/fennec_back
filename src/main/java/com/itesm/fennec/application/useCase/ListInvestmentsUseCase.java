package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.InvestmentService;
import com.itesm.fennec.domain.model.Investment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class ListInvestmentsUseCase {
    @Inject
    InvestmentService investmentService;

    public List<Investment> execute(String userId) {
        System.out.println("ListInvestmentsUseCase userId = " + userId);
        return investmentService.listInvestment(userId);
    }
}
