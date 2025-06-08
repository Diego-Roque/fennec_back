package com.itesm.fennec;

import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;
import com.itesm.fennec.domain.repository.PropertyEstimatorRepository;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class PropertyEstimatorTest {

    @Inject
    PropertyEstimatorRepository propertyEstimatorRepository;

    @Test
    public void testEstimarValorDepartamento() {
        PropertyEstimator property = new PropertyEstimator(
                "Benito Juárez",
                80,
                2,
                3,
                1
        );

        PredictionResult result = propertyEstimatorRepository.estimarValorDepartamento(property);

        assert result != null;
        assert result.getPrecioEstimado() > 0;
        assert result.getAlcaldia() != null;
        assert result.getCaracteristicas() != null;
        System.out.println("✅ Precio estimado departamento: $" + result.getPrecioEstimado());
        System.out.println("Alcaldía: " + result.getAlcaldia());
        System.out.println("Características: " + result.getCaracteristicas().getMetrosCuadrados() + "m²");
    }

    @Test
    public void testEstimarValorDepartamentoInvalido() {
        // Crear una propiedad con datos inválidos (ej. metros negativos y alcaldía nula)
        PropertyEstimator invalidProperty = new PropertyEstimator(
                null,     // Alcaldía nula
                -50,      // Metros cuadrados negativos
                1,
                2,
                0
        );

        try {
            PredictionResult result = propertyEstimatorRepository.estimarValorDepartamento(invalidProperty);
            assert result == null || result.getPrecioEstimado() <= 0 : "Se esperaba un resultado inválido.";
            System.out.println("⚠️ Resultado inválido recibido como se esperaba.");
        } catch (Exception e) {
            System.out.println("❌ Excepción esperada al estimar con datos inválidos: " + e.getMessage());
            assert e.getMessage() != null && !e.getMessage().isEmpty();
        }
    }
}
