package com.itesm.fennec.application.service;


import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class PropertyEstimatorService {
    @Inject
    PropertyEstimatorRepository propertyEstimatorRepository;

    public PropertyEstimator estimarValorDepartamento(PropertyEstimator propertyEstimator) {
        return propertyEstimatorRepository.estimarValorDepartamento(propertyEstimator);
    }

    public PropertyEstimator estimarValorCasa(PropertyEstimator propertyEstimator) {
        return propertyEstimatorRepository.estimarValorCasa(propertyEstimator);
    }
}
