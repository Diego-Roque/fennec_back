package com.itesm.fennec;

import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

@QuarkusTest
public class PropertyEstimatorTest {

    @Inject
    PropertyEstimatorRepository propertyEstimatorRepository;

    @Test
    public void testEstimarValorDepartamento() {
        // Arrange
        PropertyEstimator property = new PropertyEstimator(
                "Benito Juárez",
                80,
                2,
                3,
                1
        );

        // Act
        PredictionResult result = propertyEstimatorRepository.estimarValorDepartamento(property);

        // Assert
        assert result != null;
        assert result.getPrecioEstimado() > 0;
        assert result.getAlcaldia() != null;
        assert result.getCaracteristicas() != null;
        System.out.println("Precio estimado departamento: $" + result.getPrecioEstimado());
        System.out.println("Alcaldía: " + result.getAlcaldia());
        System.out.println("Características: " + result.getCaracteristicas().getMetrosCuadrados() + "m²");
    }
}