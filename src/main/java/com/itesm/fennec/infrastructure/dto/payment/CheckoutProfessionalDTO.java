package com.itesm.fennec.infrastructure.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutProfessionalDTO {
    private String customerEmail;
    private String uid;
}