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
    PropertyEstimatorRepository repository;

    public PredictionResult execute(PropertyEstimator propertyEstimator) {
        return repository.estimarValorDepartamento(propertyEstimator);
    }

    public PredictionResult executeHouse(PropertyEstimator propertyEstimator) {
        return repository.estimarValorCasa(propertyEstimator);
    }
}