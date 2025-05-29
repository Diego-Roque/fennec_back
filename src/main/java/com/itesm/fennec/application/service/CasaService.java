package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.*;
import com.itesm.fennec.domain.model.CasaPrecioPromedioResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

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

    @Inject
    PromedioTodasCasasUseCase obtenerPromedioTodasCasasUseCase;
    public double PromedioTodasCasas() {
        return obtenerPromedioTodasCasasUseCase.execute();
    }

    @Inject
    ObtenerPrecioM2UseCase obtenerPrecioM2UseCase;
    public Double obtenerPrecioM2UseCase() {
        return obtenerPrecioM2UseCase.execute();
    }

}
