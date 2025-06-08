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


    public DepartamentoPrecioPromedioResult obtenerPromedio(String alcaldia) {
        return departamentoRepository.obtenerPromedio(alcaldia);
    }


    public Long contarPorAlcaldia(String alcaldia) {
        return departamentoRepository.contarPorAlcaldia(alcaldia);
    }


    public double obtenerPromedioM2(String alcaldia) {
        return departamentoRepository.obtenerPromedioM2(alcaldia);
    }


    public double PromedioTodosDepartamentos() {
        return departamentoRepository.obtenerPromedioTodosDepartamentos();
    }


    public Double obtenerPrecioM2UseCase() {
        return departamentoRepository.obtenerPrecioM2();
    }

    public List<Departamento> obtenerTodosDepartamentos() {
        return departamentoRepository.obtenerTodosDepartamentos();
    }

    public List<Departamento> obtenerMenorAlPromedioDepartamentos() {
        return departamentoRepository.obtenerMenorAlPromedioDepartamentos();
    }
    @Inject
    public long obtenerNumeroCasas(){
        return departamentoRepository.obtenerNumeroDepartamentos();
    }
}