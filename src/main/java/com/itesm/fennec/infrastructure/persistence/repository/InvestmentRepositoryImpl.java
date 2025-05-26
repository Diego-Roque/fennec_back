package com.itesm.fennec.infrastructure.persistence.repository;
import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import com.itesm.fennec.infrastructure.persistence.entity.InversionEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.InvestmentMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
public class InvestmentRepositoryImpl implements InvestmentRepository {

    @Inject
    EntityManager em;

    @Override
    @Transactional
    public void save(Investment investment) {
        InversionEntity entity = InvestmentMapper.toEntity(investment);
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long investmentId) {
        InversionEntity entity = em.find(InversionEntity.class, investmentId);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    public List<Investment> findByUserId(Integer userId) {
        List<InversionEntity> entities = em.createQuery(
                        "SELECT i FROM InversionEntity i WHERE i.userId = :userId", InversionEntity.class)
                .setParameter("userId", userId)
                .getResultList();

        // En este ejemplo se cargan User y Property manualmente con solo el ID
        return entities.stream()
                .map(entity -> InvestmentMapper.toDomain(
                        entity,
                        new User(entity.getUserId(), null, null, null, null, null),
                        createPropertyStub(entity.getPropertyId())
                ))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Investment> findById(Long investmentId) {
        InversionEntity entity = em.find(InversionEntity.class, investmentId);
        if (entity == null) {
            return Optional.empty();
        }

        return Optional.of(
                InvestmentMapper.toDomain(
                        entity,
                        new User(entity.getUserId(), null, null, null, null, null),
                        createPropertyStub(entity.getPropertyId())
                )
        );
    }

    private Property createPropertyStub(Long propertyId) {
        Property property = new Property();
        property.setId(propertyId);
        return property;
    }
}