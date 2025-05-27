package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResult {
    private String tipoPropiedad;
    private double precioEstimado;
    private String alcaldia;
    private Caracteristicas caracteristicas;
    private String fechaPrediccion;


    public static class Caracteristicas {
        private double metrosCuadrados;
        private int recamaras;
        private int banos;
        private int estacionamientos;

    }
}
