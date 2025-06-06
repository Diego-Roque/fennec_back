package com.itesm.fennec.application.service;

import com.itesm.fennec.application.useCase.ActualizarNumeroUseCase;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UserService {
    @Inject
    UserRepository userRepository;
    
    public User getUserByFirebaseId(String firebaseId) {
        User user = userRepository.findByFirebaseUid(firebaseId);
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
        User user = userRepository.findByFirebaseUid(uid);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return user;
    }

    public User actualizarNumero(String userId, String numero) {
        return userRepository.updatePhoneNumber(userId, numero);
    }

    public User actualizarSuscription(String userId, String tipoRole) {
        return userRepository.updateSuscription(userId, tipoRole);
    }

    public User getRole(String userId){
        return userRepository.getRole(userId);
    }
}