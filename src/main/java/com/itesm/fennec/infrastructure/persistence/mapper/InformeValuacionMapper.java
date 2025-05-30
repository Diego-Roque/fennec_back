package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.infrastructure.persistence.entity.ReporteEntity;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InformeValuacionMapper {

    public ReporteEntity toEntity(InformeValuacion informe) {
        ReporteEntity entity = new ReporteEntity();

        entity.setDireccion(informe.getDireccion());
        entity.setColonia(informe.getColonia());
        entity.setAlcaldia(informe.getAlcaldia());
        entity.setCodigoPostal(informe.getCodigoPostal());
        entity.setTipoPropiedad(informe.getTipoPropiedad());
        entity.setRecamaras(informe.getRecamaras());
        entity.setBanos(informe.getBanos());
        entity.setEstacionamientos(informe.getEstacionamientos());
        entity.setDimensionesM2(informe.getDimensionesM2());
        entity.setAntiguedadAnos(informe.getAntiguedadAnos());
        entity.setCondicionesPropiedad(informe.getCondicionesPropiedad());
        entity.setAnotacionesExtra(informe.getAnotacionesExtra());
        entity.setValorEstimado(informe.getValorEstimado());
        entity.setAnotacionesValuacion(informe.getAnotacionesValuacion());
        entity.setIdUsuario(informe.getIdUsuario());
        
        return entity;
    }
}