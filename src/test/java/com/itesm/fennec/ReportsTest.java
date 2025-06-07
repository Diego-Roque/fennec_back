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

    @Test
    public void testListReportsWithInvalidUserId() {
        // Caso fallido: Intentar listar informes con userId inválido
        String userIdInvalido = null;

        try {
            List<InformeValuacion> informes = listarInformesUseCase.execute(userIdInvalido);

            // Si se permite userId nulo, verificar que la lista esté vacía
            if (informes != null && informes.isEmpty()) {
                System.out.println("✅ Lista vacía para userId nulo - comportamiento esperado");
            } else {
                assert false : "❌ Se esperaba lista vacía o excepción para userId nulo";
            }

        } catch (IllegalArgumentException e) {
            // Excepción esperada para userId nulo
            assert true;
            System.out.println("✅ Excepción esperada para userId nulo: " + e.getMessage());

        } catch (Exception e) {
            assert false : "❌ Excepción inesperada: " + e.getMessage();
        }
    }
}