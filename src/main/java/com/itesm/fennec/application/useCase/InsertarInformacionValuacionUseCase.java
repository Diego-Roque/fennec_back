package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.InformeValuacionService;
import com.itesm.fennec.domain.model.InformeValuacion;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class InsertarInformacionValuacionUseCase {
    @Inject
    InformeValuacionService informeValuacionService;

    public InformeValuacion execute(InformeValuacion informe) {
        informe=informeValuacionService.insertarInforme(informe);
        return informe;
    }

    }
