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
        investment.setFecha_inversion(new Date()); // current date
        investment.setBanos(2);
        investment.setRecamaras(3);
        investment.setEstacionamientos(1);
        investment.setId_usuario("user-123");
        Investment createdInvestment = investmentService.insetarInvestment(investment);
        assert createdInvestment !=null;
        assert createdInvestment.getTipo_propiedad().equals("Departamento");
        List<Investment> investments = investmentService.listInvestment("user-123");
        assert !investments.isEmpty();
        assert investments.getFirst().getTipo_propiedad().equals("Departamento");
        assert investments.getLast().getDireccion().equals("Calle Ficticia 123");

    }
}
