package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.CasaService;
import com.itesm.fennec.domain.model.Casa;
import com.itesm.fennec.domain.repository.CasaRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class ObtenerMenorAlPromedioCasasUseCase
{
    @Inject
    CasaService casaService;

    public List<Casa> execute(){
        return casaService.obtenerMenorAlPromedioCasas();
    }

}
