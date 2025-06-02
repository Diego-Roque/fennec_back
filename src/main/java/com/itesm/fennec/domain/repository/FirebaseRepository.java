package com.itesm.fennec.domain.repository;

public interface FirebaseRepository {
    String getUidFromToken(String token);
    String getEmailByFirebaseId(String firebaseId);
}