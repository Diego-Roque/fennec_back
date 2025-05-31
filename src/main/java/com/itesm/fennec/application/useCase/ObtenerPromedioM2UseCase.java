package com.itesm.fennec.application.useCase;

import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObtenerPromedioM2UseCase {

    @Inject
    CasaRepository casaRepository;
    
    @Inject
    DepartamentoRepository departamentoRepository;

    public Double execute(String alcaldia, boolean isDepartamento) {
        return isDepartamento ? 
            departamentoRepository.obtenerPromedioM2(alcaldia) :
            casaRepository.obtenerPromedioM2(alcaldia);
    }
}
