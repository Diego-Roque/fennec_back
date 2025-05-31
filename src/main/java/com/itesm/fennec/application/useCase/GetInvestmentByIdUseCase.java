package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.InvestmentService;
import com.itesm.fennec.domain.model.Investment;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class GetInvestmentByIdUseCase {
    @Inject
    InvestmentService investmentService;

    public Investment execute(Long id, String userId) {
        return investmentService.getInvestmentById(id, userId);
    }


}
