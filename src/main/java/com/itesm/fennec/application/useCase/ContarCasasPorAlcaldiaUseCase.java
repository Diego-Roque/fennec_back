package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContarCasasPorAlcaldiaUseCase {

    @Inject
    CasaRepository casaRepository;

    public Long execute(String alcaldia) {
        return casaRepository.contarPorAlcaldia(alcaldia);
    }
}