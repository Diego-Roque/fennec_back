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
    private CasaRepository casaRepository;

    @Inject
    ObtenerPromedioPrecioCasaUseCase useCase;
    public CasaPrecioPromedioResult obtenerPromedio(String alcaldia) {
        return useCase.execute(alcaldia);
    }

    @Inject
    ContarCasasPorAlcaldiaUseCase contarCasasPorAlcaldiaUseCase;
    public Long contarPorAlcaldia(String alcaldia) {
        return contarCasasPorAlcaldiaUseCase.execute(alcaldia);
    }

    @Inject
    ObtenerPromedioM2UseCase obtenerPromedioM2UseCase;
    public double obtenerPromedioM2(String alcaldia) {
        return obtenerPromedioM2UseCase.execute(alcaldia, false);
    }

    @Inject
    PromedioTodasCasasUseCase obtenerPromedioTodasCasasUseCase;
    public double PromedioTodasCasas() {
        return obtenerPromedioTodasCasasUseCase.execute();
    }

    @Inject
    ObtenerPrecioM2UseCase obtenerPrecioM2UseCase;
    public Double obtenerPrecioM2UseCase() {
        return obtenerPrecioM2UseCase.execute(false);
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
