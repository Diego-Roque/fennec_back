package com.itesm.fennec.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class PropertyListDTO {
    private String tipoPropiedad;
    private BigDecimal precioMin;
    private BigDecimal precioMax;
    private BigDecimal dimensionesMin;
    private BigDecimal dimensionesMax;
    private Integer banos;
    private Integer habitaciones;
    private Integer estacionamientos;
    private String alcaldia;

    private Integer pagina;
    private Integer limite;

    public Map<String, Object> toMap() {
        Map<String, Object> filtros = new HashMap<>();
        if (precioMin != null) filtros.put("precioMin", precioMin);
        if (precioMax != null) filtros.put("precioMax", precioMax);
        if (dimensionesMin != null) filtros.put("dimensionesMin", dimensionesMin);
        if (dimensionesMax != null) filtros.put("dimensionesMax", dimensionesMax);
        if (banos != null) filtros.put("banos", banos);
        if (habitaciones != null) filtros.put("habitaciones", habitaciones);
        if (estacionamientos != null) filtros.put("estacionamientos", estacionamientos);
        if (alcaldia != null && !alcaldia.isEmpty()) filtros.put("alcaldia", alcaldia);
        return filtros;
    }
}
