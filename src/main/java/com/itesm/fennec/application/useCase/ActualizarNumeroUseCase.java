package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActualizarNumeroUseCase {

    @Inject
    UserService userService;

    public User execute(String userId, String numero) {
        return userService.actualizarNumero(userId, numero);
    }
}
