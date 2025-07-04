package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PropertyList {
    private String tipoPropiedad;
    private List<?> propiedades;
    private int paginaActual;
    private int totalPaginas;
    private long totalResultados;
}