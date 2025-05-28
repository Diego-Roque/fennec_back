package com.itesm.fennec.application.useCase;


import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@ApplicationScoped
public class ObtenerPromedioM2UseCase {

    @Inject
    CasaRepository casaRepository;

    public Double execute(String alcaldia) {
        return casaRepository.obtenerPromedioM2(alcaldia);
    }
}
