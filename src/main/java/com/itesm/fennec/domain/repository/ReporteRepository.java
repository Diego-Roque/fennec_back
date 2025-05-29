package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.InformeValuacion;

public interface ReporteRepository {
    InformeValuacion generarReporte(InformeValuacion informeValuacion);
}
