package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;

import java.util.List;
import java.util.Map;

public interface CasaRepository {
    CasaPrecioPromedioResult obtenerPromedio(String alcaldia);
    Long contarPorAlcaldia(String alcaldia);
    Double obtenerPromedioM2(String alcaldia);
    Double obtenerPromedioTodasCasas();
    Double obtenerPrecioM2();
    List<Casa> obtenerTodasCasas();
    List<Casa> obtenerMenorAlPromedioCasas();
    List<Casa> findWithFilters(Map<String, Object> filtros, int pagina, int limite);
    Long countWithFilters(Map<String, Object> filtros);
    Long obtenerNumeroCasas();
}
