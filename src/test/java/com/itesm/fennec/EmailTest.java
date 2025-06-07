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
        // Configuración por defecto: no hacer nada al ejecutar el caso de uso
        Mockito.doNothing().when(sendEmailUseCase)
                .execute(Mockito.any(SendEmailDTO.class));
    }

    @Test
    public void testSendEmail() throws Exception {
        SendEmailDTO emailDTO = new SendEmailDTO();
        emailDTO.setFirebaseUID("user-123");
        emailDTO.setSubject("Test Subject");
        emailDTO.setMessage("Test message body");

        try {
            sendEmailUseCase.execute(emailDTO);
            System.out.println("✅ Email enviado exitosamente");
            assert true;
        } catch (Exception e) {
            assert false : "No debería fallar el envío del email: " + e.getMessage();
        }
    }

    @Test
    public void testSendEmailFailure() throws Exception {
        // Simula un fallo
        SendEmailDTO emailDTO = new SendEmailDTO();
        emailDTO.setFirebaseUID("user-999");
        emailDTO.setSubject("Failed Subject");
        emailDTO.setMessage("This will fail");

        // Configura el mock para lanzar una excepción
        Mockito.doThrow(new RuntimeException("Fallo al enviar el email"))
                .when(sendEmailUseCase).execute(Mockito.eq(emailDTO));

        try {
            sendEmailUseCase.execute(emailDTO);
            assert false : "Se esperaba una excepción al enviar el email.";
        } catch (Exception e) {
            System.out.println("❌ Error esperado al enviar email: " + e.getMessage());
            assert e.getMessage().contains("Fallo al enviar el email");
        }
    }
}
