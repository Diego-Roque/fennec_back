package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.PropertyEstimatorService;
import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EstimarValorUseCase {

    @Inject
    PropertyEstimatorService propertyEstimatorService;

    public PredictionResult execute(PropertyEstimator request) {
        return propertyEstimatorService.estimar(request);
    }

    public PredictionResult executeHouse(PropertyEstimator request) {
        return propertyEstimatorService.estimarHouse(request);
    }

}