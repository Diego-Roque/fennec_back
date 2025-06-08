package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContarCasasPorAlcaldiaUseCase {

    @Inject
    CasaService casaService;

    public Long execute(String alcaldia) {
        return casaService.contarPorAlcaldia(alcaldia);
    }
}