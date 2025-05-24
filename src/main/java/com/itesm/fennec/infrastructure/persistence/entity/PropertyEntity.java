package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "propiedad")
@Getter
@Setter
public class PropertyEntity {

    // Getters y setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad")
    private Long idPropiedad;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private BigDecimal precio;

    @Column(name = "superficie", nullable = false)
    private BigDecimal superficie;

    @Column(name = "habitaciones")
    private Integer habitaciones;

    @Column(name = "banos")
    private Integer banos;

    @Column(name = "estacionamiento")
    private Integer estacionamiento;

    @Column(name = "id_tipo_propiedad")
    private Long idTipoPropiedad;

    @Column(name = "id_colonia")
    private Long idColonia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_propiedad", insertable = false, updatable = false)
    private PropertyTypeEntity tipoPropiedad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_colonia", insertable = false, updatable = false)
    private ColoniaEntity colonia;

}

