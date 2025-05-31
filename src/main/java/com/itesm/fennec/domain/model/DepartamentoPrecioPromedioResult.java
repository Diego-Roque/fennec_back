package com.itesm.fennec.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartamentoPrecioPromedioResult {
    private String alcaldia;
    private double promedio;
}
