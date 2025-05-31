package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.repository.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PromedioTodosDepartamentosUseCase {
    @Inject
    DepartamentoRepository departamentoRepository;

    public Double execute() {
        return departamentoRepository.obtenerPromedioTodosDepartamentos();
    }
}
