package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Investment;

import java.util.List;
import java.util.Optional;

public interface InvestmentRepository {
    public Investment insertarInversion(Investment investment);
    public Investment findByInvestmentId(Long id, String userId);
    public List<Investment> findByUserId(String userId);

}
