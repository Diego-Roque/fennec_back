package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.model.Property;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.persistence.entity.InversionEntity;

public class InvestmentMapper {

    public static InversionEntity toEntity(Investment investment) {
        InversionEntity entity = new InversionEntity();
        entity.setId(investment.getId());
        entity.setUserId(investment.getUser().getId());
        entity.setPropertyId(investment.getProperty().getId());
        entity.setAmountInvested(investment.getAmountInvested());
        entity.setParticipationPercentage(investment.getParticipationPercentage());
        entity.setInvestmentDate(investment.getInvestmentDate());
        return entity;
    }

    public static Investment toDomain(InversionEntity entity, User user, Property property) {
        return new Investment(
                entity.getId(),
                property,
                user,
                entity.getAmountInvested(),
                entity.getParticipationPercentage(),
                entity.getInvestmentDate()
        );
    }
}