package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Investment {
    private BigDecimal monto_invertido;
    private BigDecimal precio_propiedad;
    private String tipo_propiedad;
    private String direccion;
    private String descripcion; //nombre
    private String alcaldia;
    private String colonia;
    private int dimensiones_m2;
    private Date fecha_inversion;
    private int banos;
    private int recamaras;
    private int estacionamientos;
    private String id_usuario;

}
