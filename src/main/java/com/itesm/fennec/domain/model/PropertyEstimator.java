package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyEstimator {
    private String alcaldia;
    private int metros_cuadrados;
    private int banos;
    private int recamaras;
    private int estacionamientos;
}
