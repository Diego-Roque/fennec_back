package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import com.itesm.fennec.infrastructure.persistence.entity.InvestmentEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.InvestmentMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class InvestmentRepositoryImpl implements InvestmentRepository, PanacheRepository<InvestmentEntity> {
    @Inject
    InvestmentRepository investmentRepository;
    @Override
    @Transactional
    public Investment insertarInversion(Investment investment) {
        persist(InvestmentMapper.toEntity(investment));
        flush();
        return investment;
    }


}
