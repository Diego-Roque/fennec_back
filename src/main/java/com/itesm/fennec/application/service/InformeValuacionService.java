package com.itesm.fennec.application.service;

import com.itesm.fennec.domain.model.InformeValuacion;
import com.itesm.fennec.domain.repository.InformeValuacionRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InformeValuacionService {
    @Inject
    InformeValuacionRepository repository;

    public InformeValuacion insertarInforme(InformeValuacion informe) {
        return repository.insertarInforme(informe);
    }
}
