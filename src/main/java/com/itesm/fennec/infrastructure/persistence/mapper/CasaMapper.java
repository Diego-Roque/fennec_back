package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.infrastructure.persistence.entity.CasaEntity;

import java.math.BigDecimal;

public class CasaMapper {
    public static CasaEntity toEntity(Casa casa) {
        CasaEntity entity = new CasaEntity();
        entity.setDireccion(casa.getDireccion());
        entity.setPrecio(casa.getPrecio().doubleValue());
        entity.setAlcaldia(casa.getAlcaldia());
        entity.setColonia(casa.getColonia());
        entity.setRecamaras(casa.getRecamaras());
        entity.setBanos(casa.getBanos());
        entity.setEstacionamientos(casa.getEstacionamientos());
        entity.setDescripcion(casa.getDescripcion());
        entity.setDimensiones_m2(casa.getDimensionesM2().intValue());
        entity.setPrecio_por_m2(casa.getPrecioPorM2().doubleValue());
        entity.setBanos_por_habitacion(casa.getBanosPorHabitacion());
        entity.setHabitaciones_totales(BigDecimal.valueOf(casa.getHabitacionesTotales()));
        return entity;
    }

    public static Casa toDomain(CasaEntity entity) {
        Casa casa = new Casa();
        casa.setDireccion(entity.getDireccion());
        casa.setPrecio(BigDecimal.valueOf(entity.getPrecio()));
        casa.setAlcaldia(entity.getAlcaldia());
        casa.setColonia(entity.getColonia());
        casa.setRecamaras(entity.getRecamaras());
        casa.setBanos(entity.getBanos());
        casa.setEstacionamientos(entity.getEstacionamientos());
        casa.setDescripcion(entity.getDescripcion());
        casa.setDimensionesM2(BigDecimal.valueOf(entity.getDimensiones_m2()));
        casa.setPrecioPorM2(BigDecimal.valueOf(entity.getPrecio_por_m2()));
        casa.setBanosPorHabitacion(entity.getBanos_por_habitacion());
        casa.setHabitacionesTotales(entity.getHabitaciones_totales().intValue());
        return casa;
    }
}