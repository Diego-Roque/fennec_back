package com.itesm.fennec;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.application.useCase.InsertarInformacionValuacionUseCase;
import com.itesm.fennec.application.useCase.ListarInformesUseCase;
import com.itesm.fennec.domain.model.InformeValuacion;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ReportsRestTest {

    @InjectMock
    FirebaseUserService firebaseUserService;

    @InjectMock
    InsertarInformacionValuacionUseCase insertarInformacionValuacionUseCase;

    @InjectMock
    ListarInformesUseCase listarInformesUseCase;

    @BeforeEach
    void setup() {
        // Mock Firebase token validation
        Mockito.when(firebaseUserService.getUidFromToken("testToken"))
                .thenReturn("user-123");

        InformeValuacion mockInformeInsertado = new InformeValuacion();

        Mockito.when(insertarInformacionValuacionUseCase.execute(Mockito.any(InformeValuacion.class)))
                .thenReturn(mockInformeInsertado);

        InformeValuacion mockInforme1 = new InformeValuacion();
        InformeValuacion mockInforme2 = new InformeValuacion();

        List<InformeValuacion> mockInformes = Arrays.asList(mockInforme1, mockInforme2);

        Mockito.when(listarInformesUseCase.execute("user-123"))
                .thenReturn(mockInformes);
    }

    @Test
    void testCreateReportEndpoint() {
        InformeValuacion informe = new InformeValuacion();

        given()
                .contentType("application/json")
                .body(informe)
                .when()
                .post("api/create-new-report")
                .then()
                .statusCode(200)
                .body("mensaje", equalTo("Reporte generado exitosamente"));
    }
}