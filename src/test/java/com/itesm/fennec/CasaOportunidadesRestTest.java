package com.itesm.fennec;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import com.itesm.fennec.application.useCase.ObtenerMenorAlPromedioCasasUseCase;
import com.itesm.fennec.domain.model.Casa;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class CasaOportunidadesRestTest {

    @InjectMock
    ObtenerMenorAlPromedioCasasUseCase obtenerMenorAlPromedioCasasUseCase;

    @InjectMock
    FirebaseUserService firebaseUserService;

    @BeforeEach
    void setup() {
        // Mock Firebase token validation
        Mockito.when(firebaseUserService.getUidFromToken("testToken"))
                .thenReturn("user-123");

        // Mock con casas de oportunidad
        Casa oportunidad1 = new Casa();
        oportunidad1.setDireccion("Av. Universidad 123");
        oportunidad1.setPrecio(new BigDecimal("2500000"));
        oportunidad1.setAlcaldia("Coyoacán");
        oportunidad1.setColonia("Del Valle");
        oportunidad1.setRecamaras(3);
        oportunidad1.setBanos(2);
        oportunidad1.setEstacionamientos(1);
        oportunidad1.setDescripcion("Casa en excelente ubicación");
        oportunidad1.setDimensionesM2(new BigDecimal("120"));
        oportunidad1.setPrecioPorM2(new BigDecimal("20833"));
        oportunidad1.setBanosPorHabitacion(new BigDecimal("0.67"));
        oportunidad1.setHabitacionesTotales(3);

        Casa oportunidad2 = new Casa();
        oportunidad2.setDireccion("Calle Revolución 456");
        oportunidad2.setPrecio(new BigDecimal("1800000"));
        oportunidad2.setAlcaldia("Benito Juárez");
        oportunidad2.setColonia("Nápoles");
        oportunidad2.setRecamaras(2);
        oportunidad2.setBanos(1);
        oportunidad2.setEstacionamientos(1);
        oportunidad2.setDescripcion("Departamento moderno");
        oportunidad2.setDimensionesM2(new BigDecimal("85"));
        oportunidad2.setPrecioPorM2(new BigDecimal("21176"));
        oportunidad2.setBanosPorHabitacion(new BigDecimal("0.50"));
        oportunidad2.setHabitacionesTotales(2);

        List<Casa> mockOportunidades = Arrays.asList(oportunidad1, oportunidad2);

        Mockito.when(obtenerMenorAlPromedioCasasUseCase.execute())
                .thenReturn(mockOportunidades);
    }

    @Test
    void testObtenerOportunidadesEndpoint() {
        given()
                .header("Authorization", "Bearer testToken")
                .when()
                .get("api/casa/oportunidades")
                .then()
                .statusCode(200)
                .body("size()", greaterThan(0))
                .body("[0].direccion", notNullValue())
                .body("[0].alcaldia", notNullValue())
                .body("[0].precio", notNullValue())
                .body("[0].colonia", notNullValue())
                .body("[0].recamaras", notNullValue())
                .body("[0].banos", notNullValue())
                .body("[0].estacionamientos", notNullValue())
                .body("[0].dimensionesM2", notNullValue())
                .body("[0].precioPorM2", notNullValue());
    }

    @Test
    void testObtenerOportunidadesConResultados() {
        given()
                .header("Authorization", "Bearer testToken")
                .when()
                .get("api/casa/oportunidades")
                .then()
                .statusCode(200)
                .body("size()", equalTo(2))
                .body("[0].direccion", equalTo("Av. Universidad 123"))
                .body("[0].alcaldia", equalTo("Coyoacán"))
                .body("[0].precio", equalTo(2500000))
                .body("[0].colonia", equalTo("Del Valle"))
                .body("[0].recamaras", equalTo(3))
                .body("[0].banos", equalTo(2))
                .body("[0].dimensionesM2", equalTo(120))
                .body("[1].direccion", equalTo("Calle Revolución 456"))
                .body("[1].alcaldia", equalTo("Benito Juárez"))
                .body("[1].precio", equalTo(1800000))
                .body("[1].recamaras", equalTo(2));
    }
}