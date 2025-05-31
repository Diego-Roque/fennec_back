package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class InvestmentService {
    @Inject
    InvestmentRepository investmentRepository;
    public Investment insetarInvestment(Investment investment) {
        return investmentRepository.insertarInversion(investment);
    }
    //public List<Investment> listarInvestment() {

    //}
    public Investment getInvestmentById(Long id, String userId) {
        return investmentRepository.findByInvestmentId(id, userId);

    }
    public List<Investment> listInvestment(String userId) {
        System.out.println("ListInvestmentService userId = " + userId);
        return investmentRepository.findByUserId(userId);
    }

}
