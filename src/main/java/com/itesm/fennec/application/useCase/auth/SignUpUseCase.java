package com.itesm.fennec.application.useCase.auth;

import com.itesm.fennec.application.service.SignUpService;
import com.itesm.fennec.infrastructure.dto.auth.SignUpDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class SignUpUseCase {
    @Inject
    SignUpService signUpService;

    public void execute(SignUpDTO signUpDTO) throws Exception {
        signUpService.signUp(signUpDTO.getEmail(), signUpDTO.getPassword());
    }
}
