package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Investment;

import java.util.List;
import java.util.Optional;

public interface InvestmentRepository {
    void save(Investment investment);
    void deleteById(Long investmentId);
    List<Investment> findByUserId(Integer userId);
    Optional<Investment> findById(Long investmentId);
}

