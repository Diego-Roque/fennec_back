// ===== PRUEBA UNITARIA DE FUNCIÓN - UN SOLO CASO =====
package com.itesm.fennec;

import com.itesm.fennec.application.useCase.SendEmailUseCase;
import com.itesm.fennec.infrastructure.dto.SendEmailDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@QuarkusTest
public class EmailTest {

    @InjectMock
    SendEmailUseCase sendEmailUseCase;

    @BeforeEach
    void setup() throws Exception {
        // Mock para que no falle con dependencias reales (no busque usuario en BD)
        Mockito.doNothing().when(sendEmailUseCase)
                .execute(Mockito.any(SendEmailDTO.class));
    }

    @Test
    public void testSendEmail() throws Exception {
        // Arrange
        SendEmailDTO emailDTO = new SendEmailDTO();
        emailDTO.setFirebaseUID("user-123");
        emailDTO.setSubject("Test Subject");
        emailDTO.setMessage("Test message body");

        // Act & Assert
        try {
            sendEmailUseCase.execute(emailDTO);
            System.out.println("✅ Email enviado exitosamente");
            assert true;
        } catch (Exception e) {
            assert false : "No debería fallar el envío del email: " + e.getMessage();
        }
    }
}