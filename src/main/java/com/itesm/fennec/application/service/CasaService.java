package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.ObtenerPromedioPrecioCasaUseCase;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CasaService {

    @Inject
    ObtenerPromedioPrecioCasaUseCase useCase;

    public CasaPrecioPromedioResult obtenerPromedio(String alcaldia) {
        return useCase.execute(alcaldia);
    }
}
