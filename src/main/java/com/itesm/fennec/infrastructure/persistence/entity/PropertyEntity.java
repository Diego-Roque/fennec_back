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

    // Getters y setters
    public Long getIdPropiedad() {
        return idPropiedad;
    }

    public void setIdPropiedad(Long idPropiedad) {
        this.idPropiedad = idPropiedad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public BigDecimal getSuperficie() {
        return superficie;
    }

    public void setSuperficie(BigDecimal superficie) {
        this.superficie = superficie;
    }

    public Integer getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(Integer habitaciones) {
        this.habitaciones = habitaciones;
    }

    public Integer getBanos() {
        return banos;
    }

    public void setBanos(Integer banos) {
        this.banos = banos;
    }

    public Integer getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(Integer estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public Long getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(Long idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public Long getIdColonia() {
        return idColonia;
    }

    public void setIdColonia(Long idColonia) {
        this.idColonia = idColonia;
    }

    public PropertyTypeEntity getTipoPropiedad() {
        return tipoPropiedad;
    }

    public void setTipoPropiedad(PropertyTypeEntity tipoPropiedad) {
        this.tipoPropiedad = tipoPropiedad;
    }

    public ColoniaEntity getColonia() {
        return colonia;
    }

    public void setColonia(ColoniaEntity colonia) {
        this.colonia = colonia;
    }
}

