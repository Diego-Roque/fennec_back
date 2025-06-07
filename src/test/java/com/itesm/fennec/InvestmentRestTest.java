package com.itesm.fennec;

import com.itesm.fennec.application.service.firebase.FirebaseUserService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import com.itesm.fennec.domain.model.Investment;

import java.math.BigDecimal;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@QuarkusTest
public class InvestmentRestTest {

    @InjectMock
    FirebaseUserService firebaseUserService;

    @BeforeEach
    void setup() {
        // Mock Firebase
        Mockito.when(firebaseUserService.getUidFromToken("testToken"))
                .thenReturn("user-123");
    }

    @Test
    void testCreateEndpoint() {
        Investment investment = new Investment();
        investment.setMonto_invertido(new BigDecimal("500000.00"));
        investment.setPrecio_propiedad(new BigDecimal("1200000.00"));
        investment.setTipo_propiedad("Departamento");
        investment.setDireccion("Calle Ficticia 123");
        investment.setDescripcion("Departamento en zona céntrica");
        investment.setAlcaldia("Benito Juárez");
        investment.setColonia("Nápoles");
        investment.setDimensiones_m2(80);
        investment.setFecha_inversion(new Date()); // current date
        investment.setBanos(2);
        investment.setRecamaras(3);
        investment.setEstacionamientos(1);
        investment.setId_usuario("user-123");

        given()
                .contentType("application/json")
                .header("Authorization", "Bearer testToken")
                .body(investment)
                .when()
                .post("api/investment")
                .then()
                .statusCode(200)
                .body("monto_invertido", equalTo(500000.00f))
                .body("precio_propiedad", equalTo(1200000.00f))
                .body("tipo_propiedad", equalTo("Departamento"))
                .body("direccion", equalTo("Calle Ficticia 123"))
                .body("descripcion", equalTo("Departamento en zona céntrica"))
                .body("alcaldia", equalTo("Benito Juárez"))
                .body("colonia", equalTo("Nápoles"))
                .body("dimensiones_m2", equalTo(80))
                .body("banos", equalTo(2))
                .body("recamaras", equalTo(3))
                .body("estacionamientos", equalTo(1))
                .body("id_usuario", equalTo("user-123"));
    }
}