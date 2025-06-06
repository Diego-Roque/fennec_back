package com.itesm.fennec.domain.repository;
import java.util.List;

import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.persistence.entity.UserEntity;

public interface UserRepository {
    User findByFirebaseId(String firebaseId);
    User findByFirebaseUid(String uid);
    User createUser(User user);
    User deleteUser(String uid);
    List<UserEntity> listAll();
    User updatePhoneNumber(String uid, String phoneNumber);
    User updateSuscription(String uid, String suscripted);
    User getRole(String uid);
}
