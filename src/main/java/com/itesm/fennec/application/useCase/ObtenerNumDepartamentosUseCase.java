package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.DepartamentoService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObtenerNumDepartamentosUseCase {
    @Inject
    DepartamentoService departamentoService;
    public long execute() {
        return departamentoService.obtenerNumeroCasas();
    }

}
