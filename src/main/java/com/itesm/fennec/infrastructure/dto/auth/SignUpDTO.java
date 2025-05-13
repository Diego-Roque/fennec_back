package com.itesm.fennec.infrastructure.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {
    private String email;
    private String password;
    private String nombre;
    private String telefono;
    private String tipoRole;
}
