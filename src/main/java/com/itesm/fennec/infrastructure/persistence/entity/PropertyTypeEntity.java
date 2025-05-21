package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_propiedad")
@Getter
@Setter
public class PropertyTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_propiedad")
    private Long idTipoPropiedad;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    // Getters y setters
    public Long getIdTipoPropiedad() {
        return idTipoPropiedad;
    }

    public void setIdTipoPropiedad(Long idTipoPropiedad) {
        this.idTipoPropiedad = idTipoPropiedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

