package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.repository.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContarDepartamentosPorAlcaldiaUseCase {

    @Inject
    DepartamentoRepository departamentoRepository;

    public Long execute(String alcaldia) {
        return departamentoRepository.contarPorAlcaldia(alcaldia);
    }
}