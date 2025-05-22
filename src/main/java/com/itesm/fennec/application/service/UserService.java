package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.GetUserProfileUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import com.itesm.fennec.infrastructure.dto.UserDTO;
import com.itesm.fennec.infrastructure.persistence.mapper.UserMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {

    @Inject
    UserRepository userRepository;

    public User getUserByFirebaseId(String firebaseId) {
        User user = userRepository.findByFirebaseId(firebaseId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

    public String getUserEmailByFirebaseId(String firebaseId) {
        User user = getUserByFirebaseId(firebaseId);
        if (user.getEmail() == null) {
            throw new IllegalArgumentException("User email is null");
        }
        return user.getEmail();
    }

    public User getUserinfoByUid(String uid) {
        User user = userRepository.findByUid(uid);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

}
