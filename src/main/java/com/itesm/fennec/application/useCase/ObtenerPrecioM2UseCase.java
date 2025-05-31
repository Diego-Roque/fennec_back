package com.itesm.fennec.application.useCase;


import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.domain.repository.DepartamentoRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObtenerPrecioM2UseCase {
    @Inject
    CasaRepository casaRepository;

    @Inject
    DepartamentoRepository departamentoRepository;

    public double execute(boolean isDepartamento) {
        return isDepartamento ?
            departamentoRepository.obtenerPrecioM2() :
            casaRepository.obtenerPrecioM2(); 
    }
}
