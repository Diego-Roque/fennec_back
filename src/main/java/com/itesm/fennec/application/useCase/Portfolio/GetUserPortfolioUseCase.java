package com.itesm.fennec.application.useCase.Portfolio;

import com.itesm.fennec.application.service.PortfolioService;
import com.itesm.fennec.domain.model.Portfolio;
import com.itesm.fennec.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor
public class GetUserPortfolioUseCase {

    private final PortfolioService portfolioService;

    public Portfolio execute(User user) {
        return portfolioService.buildPortfolioForUser(user);
    }
}
