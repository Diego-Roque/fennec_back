package com.itesm.fennec.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PredictionResult {

    @JsonProperty("tipo_propiedad")
    private String tipoPropiedad;

    @JsonProperty("precio_estimado")
    private double precioEstimado;

    private String alcaldia;

    private Caracteristicas caracteristicas;

    @JsonProperty("fecha_prediccion")
    private String fechaPrediccion;

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Caracteristicas {

        @JsonProperty("metros_cuadrados")
        private double metrosCuadrados;

        private int recamaras;
        private int banos;
        private int estacionamientos;
    }
}
