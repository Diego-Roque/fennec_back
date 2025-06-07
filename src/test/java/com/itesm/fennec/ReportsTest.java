// ===== PRUEBA UNITARIA DE FUNCIÓN =====
package com.itesm.fennec;

import com.itesm.fennec.application.useCase.InsertarInformacionValuacionUseCase;
import com.itesm.fennec.application.useCase.ListarInformesUseCase;
import com.itesm.fennec.domain.model.InformeValuacion;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.List;

@QuarkusTest
public class ReportsTest {

    @Inject
    InsertarInformacionValuacionUseCase insertarInformacionValuacionUseCase;

    @Inject
    ListarInformesUseCase listarInformesUseCase;

    @Test
    public void testCreateReport() {

        InformeValuacion informe = new InformeValuacion();

        try {
            insertarInformacionValuacionUseCase.execute(informe);

            assert true; // Si llegamos aquí, no hubo excepción
            System.out.println("✅ Informe creado exitosamente");

        } catch (Exception e) {
            assert false : "No debería fallar la creación del informe: " + e.getMessage();
        }
    }

    @Test
    public void testListReports() {
        String userId = "user-123";

        List<InformeValuacion> informes = listarInformesUseCase.execute(userId);

        assert informes != null;
        System.out.println("✅ Lista de informes obtenida: " + informes.size() + " informes");

        // Si hay informes, verificar que tengan datos válidos
        if (!informes.isEmpty()) {
            InformeValuacion primerInforme = informes.get(0);
            assert primerInforme != null;
            System.out.println("✅ Primer informe válido");
        }
    }
}
