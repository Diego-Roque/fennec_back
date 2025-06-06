package com.itesm.fennec.infrastructure.persistence.repository;

import java.util.List;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.domain.repository.UserRepository;
import com.itesm.fennec.infrastructure.persistence.entity.UserEntity;
import com.itesm.fennec.infrastructure.persistence.mapper.UserMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository, PanacheRepositoryBase<UserEntity, Integer> {

    @Override
    public User findByFirebaseId(String firebaseId) {
        try {
            FirebaseToken token = FirebaseAuth.getInstance().verifyIdToken(firebaseId);
            User user = new User();
            user.setFirebaseId(firebaseId);
            user.setEmail(token.getEmail());
            return user;
        } catch (FirebaseAuthException e) {
            throw new RuntimeException("Error retrieving email: " + e.getMessage(), e);
        }
    }

    @Override
    public User findByFirebaseUid(String uid) {
        UserEntity userEntity = find("firebaseId", uid).firstResult();
        if (userEntity == null) {
            return null;
        }
        return UserMapper.toDomain(userEntity);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        // Verificar si el usuario ya existe por firebaseId
        UserEntity existingUser = find("firebaseId", user.getFirebaseId()).firstResult();
        if (existingUser != null) {
            
            return UserMapper.toDomain(existingUser);
        }

        // Si no existe, crear nuevo usuario
        UserEntity userEntity = UserMapper.toEntity(user);
        System.out.println("email repository: " + userEntity.getEmail());
        persist(userEntity);
        flush();
        return UserMapper.toDomain(userEntity);
    }

    @Override
    @Transactional
    public User deleteUser(String uid) {
        UserEntity userEntity = find("firebaseId", uid).firstResult();
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found");
        }

        userEntity.setTipoRole("inactive");

        getEntityManager().merge(userEntity);
        flush();
        return UserMapper.toDomain(userEntity);
    }

    @Override
    public List<UserEntity> listAll() {
        return PanacheRepositoryBase.super.listAll();
    }

    @Override
    @Transactional
    public User updatePhoneNumber(String uid, String phone) {
        UserEntity userEntity = find("firebaseId", uid).firstResult();
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found");
        }
        userEntity.setTelefono(phone);
        getEntityManager().merge(userEntity);
        flush();
        return UserMapper.toDomain(userEntity);
    }

    @Override
    @Transactional
    public User updateSuscription(String uid, String suscripted){
        UserEntity userEntity = find("firebaseId", uid).firstResult();
        if (userEntity == null) {
            throw new IllegalArgumentException("User not found");
        }
        userEntity.setTipoRole(suscripted);
        getEntityManager().merge(userEntity);
        flush();
        return UserMapper.toDomain(userEntity);
    }
}