package com.itesm.fennec.infrastructure.persistence.repository;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import com.itesm.fennec.domain.repository.UserRepository;
import com.itesm.fennec.infrastructure.persistence.entity.InvestmentEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.InvestmentMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class InvestmentRepositoryImpl implements InvestmentRepository, PanacheRepository<InvestmentEntity> {

    @Inject
    UserRepository userRepository;
    @Override
    @Transactional
    public Investment insertarInversion(Investment investment) {
        persist(InvestmentMapper.toEntity(investment));
        flush();
        return investment;
    }
    @Override
    public Investment findByInvestmentId(Long investment_id, String user_id) {

        InvestmentEntity investment = find("id_inversion = ?1 and id_usuario = ?2", investment_id, user_id).firstResult();
        return InvestmentMapper.toDomain(investment);
    }

    @Override
    public List<Investment> findByUserId(String userId) {
        System.out.println("userId = " + userId);
        List<InvestmentEntity> investmentEntities = find("id_usuario = ?1", userId).list();
        System.out.println("investmentEntities = " + investmentEntities);
        System.out.println(">>>>>>");
        System.out.println(investmentEntities);

        List<Investment> investments = new ArrayList<>();
        for (InvestmentEntity investmentEntity : investmentEntities) {
            System.out.println(investmentEntity);
            investments.add(InvestmentMapper.toDomain(investmentEntity));
        }
        System.out.println("SSSS" +investments);
        return investments;
    }
}
