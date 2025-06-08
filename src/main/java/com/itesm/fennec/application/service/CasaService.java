package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.*;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import com.itesm.fennec.domain.model.Departamento;
import com.itesm.fennec.domain.model.Investment;
import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class CasaService {
    @Inject
    CasaRepository casaRepository;


    public CasaPrecioPromedioResult obtenerPromedio(String alcaldia) {
        return casaRepository.obtenerPromedio(alcaldia);
    }

    public Long contarPorAlcaldia(String alcaldia) {
        return casaRepository.contarPorAlcaldia(alcaldia);
    }

    public double obtenerPromedioM2(String alcaldia) {
        return casaRepository.obtenerPromedioM2(alcaldia);
    }


    public double PromedioTodasCasas() {
        return casaRepository.obtenerPromedioTodasCasas();
    }

    public Double obtenerPrecioM2() {
        return casaRepository.obtenerPrecioM2();
    }

    @Inject
    public List<Casa> obtenerTodasCasas(){return casaRepository.obtenerTodasCasas();}

    @Inject
    public List<Casa> obtenerMenorAlPromedioCasas(){
        return casaRepository.obtenerMenorAlPromedioCasas();
    }
    @Inject
    public long obtenerNumeroCasas(){
        return casaRepository.obtenerNumeroCasas();
    }




}
