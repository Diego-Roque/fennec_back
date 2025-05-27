package com.itesm.fennec.application.service;


import com.itesm.fennec.application.useCase.EstimarValorUseCase;
import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PropertyEstimatorService {
    @Inject
    EstimarValorUseCase useCase;

    public PredictionResult estimar(PropertyEstimator request) {
        return useCase.execute(request);
    }

    public PredictionResult estimarHouse(PropertyEstimator request) {
        return useCase.executeHouse(request);
    }
}
