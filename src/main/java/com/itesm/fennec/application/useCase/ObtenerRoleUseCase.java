package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.domain.model.User;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ObtenerRoleUseCase {

    @Inject
    UserService userService;

    public User execute(String uid) {
        return userService.getRole(uid);
    }
}
