package com.itesm.fennec.application.useCase;

import com.itesm.fennec.application.service.UserService;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.dto.UserDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetUserProfileUseCase {
    @Inject
    UserService userService;

    public void execute (UserDTO userDTO) throws Exception {
        User user = userService.getUserinfoByUid(userDTO.getUid());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getNombre());
        userDTO.setTelefono(user.getTelefono());
    }
}
