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

    public void setIdPropiedad(Long idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public void setBanos(Integer banos) {
        this.banos = banos;
    }

    public void setEstacionamiento(Integer estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public void setIdTipoPropiedad(Long idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public void setIdColonia(Long idColonia) {
        this.idColonia = idColonia;
    }

    public void setTipoPropiedad(PropertyTypeEntity tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public void setColonia(ColoniaEntity colonia) {
        this.colonia = colonia;
    }
}

