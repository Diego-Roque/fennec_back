package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PromedioTodasCasasUseCase {
    @Inject
    CasaRepository casaRepository;

    public Double execute() {
        return casaRepository.obtenerPromedioTodasCasas();
    }
}
