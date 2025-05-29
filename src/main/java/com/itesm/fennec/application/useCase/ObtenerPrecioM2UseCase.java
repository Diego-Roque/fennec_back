package com.itesm.fennec.application.useCase;


import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObtenerPrecioM2UseCase {
    @Inject
    CasaRepository casaRepository;

    public double execute() {
        return casaRepository.obtenerPrecioM2();
    }
}
