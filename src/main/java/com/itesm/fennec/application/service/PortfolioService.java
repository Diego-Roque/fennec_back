package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.Portfolio;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class PortfolioService {

    private final InvestmentRepository investmentRepository;

    public Portfolio buildPortfolioForUser(User user) {
        var investments = investmentRepository.findByUserId(user.getId());
        Portfolio portfolio = new Portfolio(user);
        investments.forEach(portfolio::addInvestment);
        return portfolio;
    }
}
