package com.itesm.fennec.infrastructure.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    private String firebaseId;
    private String email;
}