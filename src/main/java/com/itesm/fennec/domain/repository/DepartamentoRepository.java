package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;

public interface DepartamentoRepository {
    DepartamentoPrecioPromedioResult obtenerPromedio(String alcaldia);
    Long contarPorAlcaldia(String alcaldia);
    Double obtenerPromedioM2(String alcaldia);
    Double obtenerPromedioTodosDepartamentos();
    Double obtenerPrecioM2();
}
