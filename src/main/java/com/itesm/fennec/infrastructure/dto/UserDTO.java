package com.itesm.fennec.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private String uid;
    private String email;
    private String fullName;
    private String telefono;

    public UserDTO(String nombre, String email, String telefono) {
        this.fullName = nombre;
        this.email = email;
        this.telefono = telefono;
    }
}
