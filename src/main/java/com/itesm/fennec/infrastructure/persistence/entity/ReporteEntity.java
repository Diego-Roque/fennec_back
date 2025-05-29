package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Table(name = "informe_valuacion")
public class ReporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_informe;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "alcaldia")
    private String alcaldia;

    @Column(name = "codigo_postal")
    private String codigoPostal;

    @Column(name = "tipo_propiedad")
    private String tipoPropiedad;

    @Column(name = "recamaras")
    private int recamaras;

    @Column(name = "banos")
    private int banos;

    @Column(name = "estacionamientos")
    private int estacionamientos;

    @Column(name = "dimensiones_m2")
    private BigDecimal dimensionesM2;

    @Column(name = "antiguedad_anos")
    private int antiguedadAnos;

    @Column(name = "condiciones_propiedad")
    private String condicionesPropiedad;

    @Column(name = "anotaciones_extra")
    private String anotacionesExtra;

    @Column(name = "valor_estimado")
    private BigDecimal valorEstimado;

    @Column(name = "anotaciones_valuacion")
    private String anotacionesValuacion;

    @Column(name = "id_usuario")
    private String idUsuario;

    @Column(name = "fecha_creacion", insertable = false, updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion", insertable = false, updatable = false)
    private LocalDateTime fechaActualizacion;
}
