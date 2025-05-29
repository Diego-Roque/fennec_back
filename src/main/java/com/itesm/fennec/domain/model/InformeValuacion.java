package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class InformeValuacion {
    private String direccion;
    private String colonia;
    private String alcaldia;
    private String codigoPostal;
    private String tipoPropiedad;
    private int recamaras;
    private int banos;
    private int estacionamientos;
    private BigDecimal dimensionesM2;
    private int antiguedadAnos;
    private String condicionesPropiedad;
    private String anotacionesExtra;
    private BigDecimal valorEstimado;
    private String anotacionesValuacion;
    private String idUsuario;
}
