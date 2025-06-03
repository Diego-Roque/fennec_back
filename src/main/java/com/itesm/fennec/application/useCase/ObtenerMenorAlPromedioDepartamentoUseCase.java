package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.domain.model.Departamento;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ObtenerMenorAlPromedioDepartamentoUseCase {

    @Inject
    DepartamentoService departamentoService;

    public List<Departamento> execute() {
        return departamentoService.obtenerMenorAlPromedioDepartamentos();
    }

}
