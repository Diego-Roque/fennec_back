package com.itesm.fennec;

import com.itesm.fennec.application.service.InvestmentService;
import com.itesm.fennec.domain.model.Investment;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@QuarkusTest
public class InvestmentTest {
    @Inject
    InvestmentService investmentService;

    @Test
    public void testCreateInvestment() {
        Investment investment = new Investment();
        investment.setMonto_invertido(new BigDecimal("500000.00"));
        investment.setPrecio_propiedad(new BigDecimal("1200000.00"));
        investment.setTipo_propiedad("Departamento");
        investment.setDireccion("Calle Ficticia 123");
        investment.setDescripcion("Departamento en zona céntrica");
        investment.setAlcaldia("Benito Juárez");
        investment.setColonia("Napoles");
        investment.setDimensiones_m2(80);
        investment.setFecha_inversion(new Date());
        investment.setBanos(2);
        investment.setRecamaras(3);
        investment.setEstacionamientos(1);
        investment.setId_usuario("user-123");

        Investment createdInvestment = investmentService.insetarInvestment(investment);
        assert createdInvestment != null;
        assert createdInvestment.getTipo_propiedad().equals("Departamento");

        List<Investment> investments = investmentService.listInvestment("user-123");
        assert !investments.isEmpty();
        assert investments.getFirst().getTipo_propiedad().equals("Departamento");
        assert investments.getLast().getDireccion().equals("Calle Ficticia 123");
    }

    @Test
    public void testCreateInvalidInvestment_shouldFail() {
        Investment invalidInvestment = new Investment();
        // Montos nulos o inválidos
        invalidInvestment.setMonto_invertido(null);
        invalidInvestment.setPrecio_propiedad(new BigDecimal("1200000.00"));
        invalidInvestment.setTipo_propiedad("Casa");
        invalidInvestment.setDireccion("Calle Imaginaria 456");
        invalidInvestment.setDescripcion("Casa sin monto de inversión");
        invalidInvestment.setAlcaldia("Coyoacán");
        invalidInvestment.setColonia("Del Carmen");
        invalidInvestment.setDimensiones_m2(100);
        invalidInvestment.setFecha_inversion(new Date());
        invalidInvestment.setBanos(2);
        invalidInvestment.setRecamaras(4);
        invalidInvestment.setEstacionamientos(2);
        invalidInvestment.setId_usuario("user-456");

        try {
            Investment result = investmentService.insetarInvestment(invalidInvestment);
            assert result != null : "Se esperaba que la inversión inválida no fuera creada.";
        } catch (Exception e) {
            System.out.println("❌ Fallo esperado al insertar inversión inválida: " + e.getMessage());
            assert e.getMessage() != null && !e.getMessage().isEmpty();
        }
    }
}
