package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.infrastructure.dto.GenerarReporteDTO;
import com.itesm.fennec.infrastructure.persistence.entity.ReporteEntity;

import java.math.BigDecimal;

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
        System.out.println("Entidad mapeada: " + entity);

        return entity;
    }

    public static InformeValuacion toDomain(GenerarReporteDTO dto) {
        InformeValuacion informe = new InformeValuacion();

        informe.setDireccion(dto.direccion);
        informe.setColonia(dto.colonia);
        informe.setAlcaldia(dto.alcaldia);
        informe.setCodigoPostal(dto.codigoPostal);
        informe.setTipoPropiedad(dto.tipoPropiedad);
        informe.setRecamaras(dto.recamaras);
        informe.setBanos(dto.banos);
        informe.setEstacionamientos(dto.estacionamientos);
        informe.setDimensionesM2(BigDecimal.valueOf(dto.dimensionesM2));
        informe.setAntiguedadAnos(dto.antiguedadAnos);
        informe.setCondicionesPropiedad(dto.condicionesPropiedad);
        informe.setAnotacionesExtra(dto.anotacionesExtra);
        informe.setValorEstimado(BigDecimal.valueOf(dto.valorEstimado));
        informe.setAnotacionesValuacion(dto.anotacionesValuacion);
        return informe;
    }
}
