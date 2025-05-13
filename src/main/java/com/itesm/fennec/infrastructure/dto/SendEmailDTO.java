// SendEmailDTO.java
package com.itesm.fennec.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SendEmailDTO {
    private String firebaseUID;
    private String subject;
    private String message;
}
