package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ObtenerPromedioPrecioCasaUseCase {

    @Inject
    CasaRepository casaRepository;

    public CasaPrecioPromedioResult execute(String alcaldia) {
        return casaRepository.obtenerPromedio(alcaldia);
    }
}
