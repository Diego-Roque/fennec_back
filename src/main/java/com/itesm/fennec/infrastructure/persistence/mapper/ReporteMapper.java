package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.infrastructure.persistence.entity.ReporteEntity;

public class ReporteMapper {

    public static ReporteEntity toEntity(InformeValuacion model) {
        ReporteEntity entity = new ReporteEntity();

        entity.setDireccion(model.getDireccion());
        entity.setColonia(model.getColonia());
        entity.setAlcaldia(model.getAlcaldia());
        entity.setCodigoPostal(model.getCodigoPostal());
        entity.setTipoPropiedad(model.getTipoPropiedad());
        entity.setRecamaras(model.getRecamaras());
        entity.setBanos(model.getBanos());
        entity.setEstacionamientos(model.getEstacionamientos());
        entity.setDimensionesM2(model.getDimensionesM2());
        entity.setAntiguedadAnos(model.getAntiguedadAnos());
        entity.setCondicionesPropiedad(model.getCondicionesPropiedad());
        entity.setAnotacionesExtra(model.getAnotacionesExtra());
        entity.setValorEstimado(model.getValorEstimado());
        entity.setAnotacionesValuacion(model.getAnotacionesValuacion());
        entity.setIdUsuario(model.getIdUsuario());

        return entity;
    }
}
