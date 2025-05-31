package com.itesm.fennec.infrastructure.persistence.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="inversion")
public class InvestmentEntity  extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_inversion;

    @Column(name = "monto_invertido" )
    private BigDecimal monto_invertido;

    @Column(name = "precio_propiedad", nullable = false)
    private BigDecimal precio_propiedad;

    @Column(name = "tipo_propiedad", nullable = false)
    private String tipo_propiedad;

    @Column(name = "direccion", nullable = false)
    private String direccion;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "alcaldia")
    private String alcaldia;

    @Column(name = "colonia")
    private String colonia;

    @Column(name = "dimensiones_m2")
    private int dimensiones_m2;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inversion")
    private Date fecha_inversion;

    @Column(name = "banos")
    private int banos;

    @Column(name = "recamaras")
    private int recamaras;

    @Column(name = "estacionamientos")
    private int estacionamientos;


    @Column(name = "id_usuario", nullable = false)
    private String id_usuario;
}
