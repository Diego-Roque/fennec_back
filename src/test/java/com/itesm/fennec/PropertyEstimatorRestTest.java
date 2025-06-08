package com.itesm.fennec;

import com.itesm.fennec.application.service.PropertyEstimatorService;
import com.itesm.fennec.domain.model.PropertyEstimator;
import com.itesm.fennec.domain.model.PredictionResult;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class PropertyEstimatorRestTest {

    @InjectMock
    PropertyEstimatorService propertyEstimatorService;

    @BeforeEach
    void setup() {

        PredictionResult.Caracteristicas deptCaracteristicas = new PredictionResult.Caracteristicas(
                80.0,
                3,
                2,
                1
        );

        PredictionResult mockDepartmentResult = new PredictionResult(
                "Departamento",
                2800000.0,
                "Benito Juárez",
                deptCaracteristicas,
                "2025-06-06"
        );


        PredictionResult.Caracteristicas houseCaracteristicas = new PredictionResult.Caracteristicas(
                120.0,
                4,
                3,
                2
        );

        PredictionResult mockHouseResult = new PredictionResult(
                "Casa",
                4200000.0,
                "Coyoacán",
                houseCaracteristicas,
                "2025-06-06"
        );

        // Configurar mocks del servicio
        Mockito.when(propertyEstimatorService.estimar(Mockito.any()))
                .thenReturn(mockDepartmentResult);

        Mockito.when(propertyEstimatorService.estimarHouse(Mockito.any()))
                .thenReturn(mockHouseResult);
    }

    @Test
    void testEstimateDepartmentEndpoint() {
        PropertyEstimator property = new PropertyEstimator(
                "Benito Juárez",
                80,
                2,
                3,
                1
        );

        given()
                .contentType("application/json")
                .body(property)
                .when()
                .post("api/estimar/departamento")
                .then()
                .statusCode(200)
                .body("precio_estimado", notNullValue())
                .body("precio_estimado", greaterThan(0.0f))
                .body("alcaldia", notNullValue())
                .body("caracteristicas", notNullValue())
                .body("caracteristicas.metros_cuadrados", equalTo(80.0f))
                .body("caracteristicas.recamaras", equalTo(3))
                .body("caracteristicas.banos", equalTo(2))
                .body("caracteristicas.estacionamientos", equalTo(1));
    }

    @Test
    void testEstimateHouseEndpoint() {
        PropertyEstimator property = new PropertyEstimator(
                "Coyoacán",
                120,
                3,
                4,
                2
        );

        given()
                .contentType("application/json")
                .body(property)
                .when()
                .post("api/estimar/casa")
                .then()
                .statusCode(200)
                .body("precio_estimado", notNullValue())
                .body("precio_estimado", greaterThan(0.0f))
                .body("alcaldia", notNullValue())
                .body("caracteristicas", notNullValue())
                .body("caracteristicas.metros_cuadrados", equalTo(120.0f))
                .body("caracteristicas.recamaras", equalTo(4))
                .body("caracteristicas.banos", equalTo(3))
                .body("caracteristicas.estacionamientos", equalTo(2));
    }
}
