package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="departamento")
public class DepartamentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_casa;

    @Column(name="direccion")
    private String direccion;

    @Column(name="precio")
    private double precio;

    @Column(name="alcaldia")
    private String alcaldia;

    @Column(name="colonia")
    private String colonia;

    @Column(name="recamaras")
    private int recamaras;

    @Column(name="banos")
    private int banos;

    @Column(name="estacionamientos")
    private int estacionamientos;

    @Column(name="descripcion")
    private String descripcion;

    @Column(name="dimensiones_m2")
    private int dimensiones_m2;

    @Column(name="precio_por_m2")
    private double precio_por_m2;

    @Column(name="banos_por_habitacion")
    private BigDecimal banos_por_habitacion;

    @Column(name="habitaciones_totales")
    private BigDecimal habitaciones_totales;

}
