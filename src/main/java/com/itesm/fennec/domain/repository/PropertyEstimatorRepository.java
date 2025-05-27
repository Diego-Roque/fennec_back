package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.PropertyEstimator;

public interface PropertyEstimatorRepository {
    PropertyEstimator estimarValorDepartamento(PropertyEstimator propertyEstimator);
    PropertyEstimator estimarValorCasa(PropertyEstimator propertyEstimator);
}
