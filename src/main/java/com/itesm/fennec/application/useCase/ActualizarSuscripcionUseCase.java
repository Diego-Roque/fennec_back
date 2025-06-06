package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ActualizarSuscripcionUseCase {


    @Inject
    UserService userService;

    public User execute(String userId, String suscripcion) {
        return userService.actualizarSuscription(userId, suscripcion);
    }
}
