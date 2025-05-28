package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;

public interface CasaRepository {
    CasaPrecioPromedioResult obtenerPromedio(String alcaldia);
    Long contarPorAlcaldia(String alcaldia);
    Double obtenerPromedioM2(String alcaldia);
    Double obtenerPromedioTodasCasas();
}
