package com.itesm.fennec.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
public class PropertyResponseDTO {
    private Long idPropiedad;
    private String titulo;
    private String descripcion;
    private BigDecimal precio;
    private BigDecimal superficie;
    private Integer habitaciones;
    private Integer banos;
    private Integer estacionamiento;
    private String tipoPropiedad;
    private String colonia;
    private String municipio;
    private String estado;
    private List<String> imagenes;

}
