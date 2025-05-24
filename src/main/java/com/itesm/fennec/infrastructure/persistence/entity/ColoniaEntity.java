package com.itesm.fennec.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "colonia")
@Getter
@Setter
public class ColoniaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_colonia")
    private Long idColonia;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "id_municipio")
    private Long idMunicipio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_municipio", insertable = false, updatable = false)
    private MunicipioEntity municipio;


}

