package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.ContarCasasPorAlcaldiaUseCase;
import com.itesm.fennec.application.useCase.ObtenerPromedioM2UseCase;
import com.itesm.fennec.application.useCase.ObtenerPromedioPrecioCasaUseCase;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;

@ApplicationScoped
public class CasaService {

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
        return obtenerPromedioM2UseCase.execute(alcaldia);
    }

}
