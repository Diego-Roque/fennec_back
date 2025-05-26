package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
@ApplicationScoped
@RequiredArgsConstructor
public class InvestmentService {

    private final InvestmentRepository investmentRepository;

    public Investment createInvestment(User user, Property property, BigDecimal amount, BigDecimal percentage) {
        Investment investment = new Investment(
                null,
                property,
                user,
                amount,
                percentage,
                LocalDate.now()
        );
        investmentRepository.save(investment);
        return investment;
    }

    public void deleteInvestment(Long investmentId) {
        investmentRepository.deleteById(investmentId);
    }
}
