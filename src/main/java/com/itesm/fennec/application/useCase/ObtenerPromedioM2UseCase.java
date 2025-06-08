package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.application.service.DepartamentoService;
import com.itesm.fennec.domain.repository.CasaRepository;
import com.itesm.fennec.domain.repository.DepartamentoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObtenerPromedioM2UseCase {

    @Inject
    CasaService casaService;

    @Inject
    DepartamentoService departamentoService;

    public Double execute(String alcaldia, boolean isDepartamento) {
        return isDepartamento
                ? departamentoService.obtenerPromedioM2(alcaldia)
                : casaService.obtenerPromedioM2(alcaldia);
    }
}
