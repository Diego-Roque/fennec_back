package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ObtenerPromedioPrecioCasaUseCase {

    @Inject
    CasaService casaService;

    public CasaPrecioPromedioResult execute(String alcaldia) {
        return casaService.obtenerPromedio(alcaldia);
    }
}
