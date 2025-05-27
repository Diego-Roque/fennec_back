package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InvestmentService {
    @Inject
    InvestmentRepository investmentRepository;
    public Investment insetarInvestment(Investment investment) {
        return investmentRepository.insertarInversion(investment);
    }

}
