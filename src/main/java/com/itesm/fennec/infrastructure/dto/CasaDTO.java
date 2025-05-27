package com.itesm.fennec.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class CasaDTO {
    private Long idCasa;
    private String direccion;
    private BigDecimal precio;
    private String alcaldia;
    private String colonia;
    private Integer recamaras;
    private Integer banos;
    private Integer estacionamientos;
    private String descripcion;
    private BigDecimal dimensionesM2;
    private BigDecimal precioPorM2;
    private BigDecimal banosPorHabitacion;
    private Integer habitacionesTotales;
}
