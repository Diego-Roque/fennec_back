package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.PropertyEstimatorService;
import com.itesm.fennec.domain.model.PropertyEstimator;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class EstimarValorUseCase {
    @Inject
    PropertyEstimatorService propertyEstimatorService;
    public PropertyEstimator execute(PropertyEstimator propertyEstimator) {
        propertyEstimator = propertyEstimatorService.estimarValorDepartamento(propertyEstimator);
        return propertyEstimator;
    }

    public PropertyEstimator executeCasa(PropertyEstimator propertyEstimator) {
        propertyEstimator = propertyEstimatorService.estimarValorCasa(propertyEstimator);
        return propertyEstimator;
    }
}
