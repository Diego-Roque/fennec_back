package com.itesm.fennec.domain.repository;
import com.itesm.fennec.domain.model.User;

public interface UserRepository {
    User findByFirebaseId(String firebaseId);
    User createUser(User user);
}
