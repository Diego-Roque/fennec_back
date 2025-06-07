// ===== PRUEBA UNITARIA DE FUNCIÓN =====
package com.itesm.fennec;

import com.itesm.fennec.application.useCase.ObtenerMenorAlPromedioCasasUseCase;
import com.itesm.fennec.domain.model.Casa;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

@QuarkusTest
public class CasaOportunidadesTest {

    @Inject
    ObtenerMenorAlPromedioCasasUseCase obtenerMenorAlPromedioCasasUseCase;

    @Test
    public void testObtenerOportunidades() {
        // Act
        try {
            List<Casa> oportunidades = obtenerMenorAlPromedioCasasUseCase.execute();

            // Assert
            assert oportunidades != null : "La lista de oportunidades no debería ser null";
            System.out.println("✅ Oportunidades obtenidas: " + oportunidades.size() + " casas");

            // Si hay oportunidades, verificar que tengan datos válidos
            if (!oportunidades.isEmpty()) {
                Casa primeraOportunidad = oportunidades.get(0);
                assert primeraOportunidad != null : "La casa no debería ser null";
                assert primeraOportunidad.getPrecio() != null : "El precio no debería ser null";
                assert primeraOportunidad.getAlcaldia() != null : "La alcaldía no debería ser null";
                System.out.println("✅ Primera oportunidad válida: " + primeraOportunidad.getAlcaldia() +
                        " - $" + primeraOportunidad.getPrecio());
            }

        } catch (Exception e) {
            assert false : "No debería fallar la obtención de oportunidades: " + e.getMessage();
        }
    }
}
