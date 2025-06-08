package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;
import com.itesm.fennec.domain.repository.DepartamentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;


@ApplicationScoped
public class ObtenerPromedioPrecioDepartamentoUseCase {

    @Inject
    DepartamentoService departamentoService;

    public DepartamentoPrecioPromedioResult execute(String alcaldia) {
        return departamentoService.obtenerPromedio(alcaldia);
    }
}
