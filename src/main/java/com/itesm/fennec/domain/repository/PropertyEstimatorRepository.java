package com.itesm.fennec.domain.repository;

import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.model.PropertyEstimator;

public interface PropertyEstimatorRepository {
    PredictionResult estimarValorDepartamento(PropertyEstimator propertyEstimator);
    PredictionResult estimarValorCasa(PropertyEstimator propertyEstimator);
}
