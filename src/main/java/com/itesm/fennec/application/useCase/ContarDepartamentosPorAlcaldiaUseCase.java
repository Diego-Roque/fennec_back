package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ContarDepartamentosPorAlcaldiaUseCase {

    @Inject
    DepartamentoService departamentoService;

    public Long execute(String alcaldia) {
        return departamentoService.contarPorAlcaldia(alcaldia);
    }
}