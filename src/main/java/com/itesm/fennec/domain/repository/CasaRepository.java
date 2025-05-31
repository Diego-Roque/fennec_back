package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;

import java.util.List;

public interface CasaRepository {
    CasaPrecioPromedioResult obtenerPromedio(String alcaldia);
    Long contarPorAlcaldia(String alcaldia);
    Double obtenerPromedioM2(String alcaldia);
    Double obtenerPromedioTodasCasas();
    Double obtenerPrecioM2();
    List<Casa> obtenerTodasCasas();
}
