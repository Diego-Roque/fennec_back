package com.itesm.fennec.application.service.firebase;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.itesm.fennec.domain.repository.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FirebaseUserService {

    @Inject
    UserRepository userRepository;

    public String getEmailByFirebaseId(String firebaseId) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(firebaseId);
            return token.getEmail();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error retrieving email: " + e.getMessage(), e);
        }
    }
}