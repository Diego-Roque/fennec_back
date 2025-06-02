package com.itesm.fennec.application.useCase;


import com.itesm.fennec.application.service.InformeValuacionService;
import com.itesm.fennec.domain.model.InformeValuacion;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
@ApplicationScoped
public class ListarInformesUseCase {
    @Inject
    InformeValuacionService informeService;


    public List<InformeValuacion> execute(String userId) {
        System.out.println("ListInformesUseCase userId = " + userId);
        return informeService.listarInformes(userId);
    }
}
