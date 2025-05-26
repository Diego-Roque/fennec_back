package com.itesm.fennec.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Investment {
    private Long id;
    private Property property;
    private User user;
    private BigDecimal amountInvested;
    private BigDecimal participationPercentage; // opcional
    private LocalDate investmentDate;

}
