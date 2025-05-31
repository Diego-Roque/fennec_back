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
        entity.setId_usuario(investment.getId_usuario());

        return entity;
    }
    public static Investment toDomain(InvestmentEntity entity) {
        Investment investment = new Investment();
        investment.setAlcaldia(entity.getAlcaldia());
        investment.setColonia(entity.getColonia());
        investment.setDescripcion(entity.getDescripcion());
        investment.setDireccion(entity.getDireccion());
        investment.setDimensiones_m2(entity.getDimensiones_m2());
        investment.setFecha_inversion(entity.getFecha_inversion());
        investment.setMonto_invertido(entity.getMonto_invertido());
        investment.setPrecio_propiedad(entity.getPrecio_propiedad());
        investment.setTipo_propiedad(entity.getTipo_propiedad());
        investment.setBanos(entity.getBanos());
        investment.setRecamaras(entity.getRecamaras());
        investment.setEstacionamientos(entity.getEstacionamientos());
        investment.setId_usuario(entity.getId_usuario());
        return investment;
    }
}
