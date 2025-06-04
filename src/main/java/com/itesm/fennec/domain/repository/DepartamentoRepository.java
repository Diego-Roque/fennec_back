package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;

import java.util.List;
import java.util.Map;

public interface DepartamentoRepository {
    DepartamentoPrecioPromedioResult obtenerPromedio(String alcaldia);
    Long contarPorAlcaldia(String alcaldia);
    Double obtenerPromedioM2(String alcaldia);
    Double obtenerPromedioTodosDepartamentos();
    Double obtenerPrecioM2();
    List<Departamento> obtenerTodosDepartamentos();
    List<Departamento> obtenerMenorAlPromedioDepartamentos();
    List<Departamento> findWithFilters(Map<String, Object> filtros, int pagina, int limite);
    Long countWithFilters(Map<String, Object> filtros);
}

