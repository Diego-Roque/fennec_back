package com.itesm.fennec.application.useCase.Investment;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
@ApplicationScoped
@RequiredArgsConstructor
public class GetInvestmentByIdUseCase {

    private final InvestmentRepository investmentRepository;

    public Optional<Investment> execute(Long investmentId) {
        return investmentRepository.findById(investmentId);
    }
}
