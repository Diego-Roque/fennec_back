package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "municipio")
@Getter
@Setter
public class MunicipioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_municipio")
    private Long idMunicipio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "id_estado")
    private Long idEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado", insertable = false, updatable = false)
    private EstadoEntity estado;

    // Getters y setters
    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }

    public EstadoEntity getEstado() {
        return estado;
    }

    public void setEstado(EstadoEntity estado) {
        this.estado = estado;
    }
}

