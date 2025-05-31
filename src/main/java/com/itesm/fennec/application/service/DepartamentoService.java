package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.*;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.DepartamentoPrecioPromedioResult;

import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import com.itesm.fennec.domain.repository.InvestmentRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class DepartamentoService {
    @Inject
    DepartamentoRepository departamentoRepository;

    @Inject
    ObtenerPromedioPrecioDepartamentoUseCase useCase;
    public DepartamentoPrecioPromedioResult obtenerPromedio(String alcaldia) {
        return useCase.execute(alcaldia);
    }

    @Inject
    ContarDepartamentosPorAlcaldiaUseCase contarDepartamentosPorAlcaldiaUseCase;
    public Long contarPorAlcaldia(String alcaldia) {
        return contarDepartamentosPorAlcaldiaUseCase.execute(alcaldia);
    }

    @Inject
    ObtenerPromedioM2UseCase obtenerPromedioM2UseCase;
    public double obtenerPromedioM2(String alcaldia) {
        return obtenerPromedioM2UseCase.execute(alcaldia, true);
    }

    @Inject
    PromedioTodosDepartamentosUseCase obtenerPromedioTodosDepartamentosUseCase;
    public double PromedioTodosDepartamentos() {
        return obtenerPromedioTodosDepartamentosUseCase.execute();
    }

    @Inject
    ObtenerPrecioM2UseCase obtenerPrecioM2UseCase;
    public Double obtenerPrecioM2UseCase() {
        return obtenerPrecioM2UseCase.execute(true);
    }

    public List<Departamento> obtenerTodosDepartamentos() {
        return departamentoRepository.obtenerTodosDepartamentos();
    }
}