package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.ReporteUseCase;
import com.itesm.fennec.domain.model.InformeValuacion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReporteService {

    @Inject
    ReporteUseCase useCase;

    public void generar(InformeValuacion informe) {
        useCase.generarReporte(informe);
    }
}
