package com.itesm.fennec.infrastructure.persistence.repository;

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
        UserEntity userEntity = find("firebaseId", firebaseId).firstResult();
        if (userEntity != null) {
            return UserMapper.toDomain(userEntity);
        }
        return null;
    }

    public UserEntity findUserById(int id) {
        return findById(id);
    }

    @Override
    @Transactional
    public User createUser(User user) {
        persist(UserMapper.toEntity(user));
        flush();
        return user;
    }

}
