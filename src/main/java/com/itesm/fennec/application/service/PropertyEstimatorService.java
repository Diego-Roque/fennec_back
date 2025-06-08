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
    PropertyEstimatorRepository repository;


    public PredictionResult estimar(PropertyEstimator propertyEstimator) {
        return repository.estimarValorDepartamento(propertyEstimator);
    }

    public PredictionResult estimarHouse(PropertyEstimator propertyEstimator) {
        return repository.estimarValorCasa(propertyEstimator);
    }
}
