package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PromedioTodosDepartamentosUseCase {
    @Inject
    DepartamentoService departamentoService;

    public Double execute() {
        return departamentoService.PromedioTodosDepartamentos();
    }
}
