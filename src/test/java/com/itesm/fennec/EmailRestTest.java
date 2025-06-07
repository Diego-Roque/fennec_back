package com.itesm.fennec;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.application.useCase.SendEmailUseCase;
import com.itesm.fennec.infrastructure.dto.SendEmailDTO;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class EmailRestTest {

    @InjectMock
    SendEmailUseCase sendEmailUseCase;

    @InjectMock
    FirebaseUserService firebaseUserService;

    @BeforeEach
    void setup() throws Exception {
        // Mock Firebase token validation
        Mockito.when(firebaseUserService.getUidFromToken("testToken"))
                .thenReturn("user-123");

        // Mock para envío exitoso
        Mockito.doNothing().when(sendEmailUseCase)
                .execute(Mockito.any(SendEmailDTO.class));
    }

    @Test
    void testSendEmailEndpoint() {

        SendEmailDTO emailDTO = new SendEmailDTO();
        emailDTO.setFirebaseUID("user-123");
        emailDTO.setSubject("Test Subject");
        emailDTO.setMessage("Test email body");


        given()
                .contentType("application/json")
                .header("Authorization", "Bearer testToken")
                .body(emailDTO)
                .when()
                .post("api/email")
                .then()
                .statusCode(200)
                .body(equalTo("Email sent successfully"));
    }
}