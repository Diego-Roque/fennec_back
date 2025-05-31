package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.infrastructure.persistence.entity.CasaEntity;
import com.itesm.fennec.infrastructure.persistence.entity.DepartamentoEntity;

import java.math.BigDecimal;

public class DepartamentoMapper {
    public static Departamento toDomain(DepartamentoEntity entity) {
        Departamento departamento = new Departamento();
        departamento.setDireccion(entity.getDireccion());
        departamento.setPrecio(BigDecimal.valueOf(entity.getPrecio()));
        departamento.setAlcaldia(entity.getAlcaldia());
        departamento.setColonia(entity.getColonia());
        departamento.setRecamaras(entity.getRecamaras());
        departamento.setBanos(entity.getBanos());
        departamento.setEstacionamientos(entity.getEstacionamientos());
        departamento.setDescripcion(entity.getDescripcion());
        departamento.setDimensionesM2(BigDecimal.valueOf(entity.getDimensiones_m2()));
        departamento.setPrecioPorM2(BigDecimal.valueOf(entity.getPrecio_por_m2()));
        departamento.setBanosPorHabitacion(entity.getBanos_por_habitacion());
        departamento.setHabitacionesTotales(entity.getHabitaciones_totales().intValue());
        return departamento;
    }
}
