package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;

import java.math.BigDecimal;

public interface CasaRepository {
    CasaPrecioPromedioResult obtenerPromedio(String alcaldia);
}
