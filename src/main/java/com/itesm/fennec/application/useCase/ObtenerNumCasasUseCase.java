package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.domain.model.Casa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class ObtenerNumCasasUseCase {

    @Inject
    CasaService casaService;
    public long execute() {
        return casaService.obtenerNumeroCasas();
    }
}
