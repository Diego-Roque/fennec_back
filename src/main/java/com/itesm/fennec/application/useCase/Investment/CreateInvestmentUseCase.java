package com.itesm.fennec.application.useCase.Investment;

import com.itesm.fennec.application.service.InvestmentService;
import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
@ApplicationScoped
@RequiredArgsConstructor
public class CreateInvestmentUseCase {

    private final InvestmentService investmentService;

    public Investment execute(User user, Property property, BigDecimal amount, BigDecimal percentage) {
        return investmentService.createInvestment(user, property, amount, percentage);
    }
}
