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
    public static InformeValuacion toDomain(ReporteEntity entity) {
        InformeValuacion informeValuacion = new InformeValuacion();
        informeValuacion.setDireccion(entity.getDireccion());
        informeValuacion.setColonia(entity.getColonia());
        informeValuacion.setAlcaldia(entity.getAlcaldia());
        informeValuacion.setCodigoPostal(entity.getCodigoPostal());
        informeValuacion.setTipoPropiedad(entity.getTipoPropiedad());
        informeValuacion.setRecamaras(entity.getRecamaras());
        informeValuacion.setBanos(entity.getBanos());
        informeValuacion.setEstacionamientos(entity.getEstacionamientos());
        informeValuacion.setDimensionesM2(entity.getDimensionesM2());
        informeValuacion.setAntiguedadAnos(entity.getAntiguedadAnos());
        informeValuacion.setCondicionesPropiedad(entity.getCondicionesPropiedad());
        informeValuacion.setAnotacionesExtra(entity.getAnotacionesExtra());
        informeValuacion.setValorEstimado(entity.getValorEstimado());
        informeValuacion.setAnotacionesValuacion(entity.getAnotacionesValuacion());
        informeValuacion.setIdUsuario(entity.getIdUsuario());
        informeValuacion.setFechaActualizacion(entity.getFechaActualizacion());
        informeValuacion.setFechaCreacion(entity.getFechaCreacion());


        return informeValuacion;
    }
}