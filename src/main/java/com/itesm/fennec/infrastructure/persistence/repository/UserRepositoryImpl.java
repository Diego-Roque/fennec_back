package com.itesm.fennec.infrastructure.persistence.repository;

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
    @Transactional
    public User createUser(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        persist(userEntity);
        flush();
        return UserMapper.toDomain(userEntity);
    }
}
