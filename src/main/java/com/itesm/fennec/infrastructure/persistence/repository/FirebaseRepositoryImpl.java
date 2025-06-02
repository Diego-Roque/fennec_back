package com.itesm.fennec.infrastructure.persistence.repository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.itesm.fennec.domain.repository.FirebaseRepository;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class FirebaseRepositoryImpl implements FirebaseRepository {

    @Override
    public String getUidFromToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (Exception e) {
            throw new RuntimeException("Token inválido", e);
        }
    }

    @Override
    public String getEmailByFirebaseId(String firebaseId) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(firebaseId);
            return token.getEmail();
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error al obtener el correo: " + e.getMessage(), e);
        }
    }
}
