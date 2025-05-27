package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.infrastructure.persistence.entity.InvestmentEntity;

public class InvestmentMapper {
    public static InvestmentEntity toEntity(Investment investment) {
        InvestmentEntity entity = new InvestmentEntity();
        entity.setAlcaldia(investment.getAlcaldia());
        entity.setColonia(investment.getColonia());
        entity.setDescripcion(investment.getDescripcion());
        entity.setDireccion(investment.getDireccion());
        entity.setDimensiones_m2(investment.getDimensiones_m2());
        entity.setFecha_inversion(investment.getFecha_inversion());
        entity.setMonto_invertido(investment.getMonto_invertido());
        entity.setPrecio_propiedad(investment.getPrecio_propiedad());
        entity.setTipo_propiedad(investment.getTipo_propiedad());
        entity.setBanos(investment.getBanos());
        entity.setRecamaras(investment.getRecamaras());
        entity.setEstacionamientos(investment.getEstacionamientos());

        return entity;
    }
}
