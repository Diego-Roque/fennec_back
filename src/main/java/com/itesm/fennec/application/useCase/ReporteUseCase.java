package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.domain.repository.ReporteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ReporteUseCase {

    @Inject
    ReporteRepository repository;

    public void generarReporte(InformeValuacion informe) {
        repository.generarReporte(informe);
    }
}