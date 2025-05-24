package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "imagenes_propiedad")
@Getter
@Setter
public class ImagenPropiedadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private Long idImagen;

    @Column(name = "url_imagen", nullable = false)
    private String urlImagen;

    @Column(name = "id_propiedad")
    private Long idPropiedad;


}
