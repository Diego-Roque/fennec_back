package com.itesm.fennec.infrastructure.persistence.mapper;

import com.itesm.fennec.domain.model.User;
import com.itesm.fennec.infrastructure.dto.UserDTO;
import com.itesm.fennec.infrastructure.persistence.entity.UserEntity;

public class UserMapper {
    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId_usuario(user.getId());
        userEntity.setFirebaseId(user.getFirebaseId());
        userEntity.setNombre(user.getNombre());
        userEntity.setTelefono(user.getTelefono());
        userEntity.setTipoRole(user.getTipoRole());
        userEntity.setEmail(user.getEmail());
        return userEntity;
    }

    public static User toDomain(UserEntity userEntity) {
        User user = new User();
        user.setId(userEntity.getId_usuario());
        user.setFirebaseId(userEntity.getFirebaseId());
        user.setNombre(userEntity.getNombre());
        user.setTelefono(userEntity.getTelefono());
        user.setTipoRole(userEntity.getTipoRole());
        user.setEmail(userEntity.getEmail());
        return user;
    }

    public static UserDTO toDTO(User user) {
        System.out.println("Converting user to DTO: " + user.getEmail());
        UserDTO userDTO = new UserDTO();
        userDTO.setUid(user.getFirebaseId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFullName(user.getNombre());
        userDTO.setTelefono(user.getTelefono());
        System.out.println("DTO created successfully");
        return userDTO;
    }
}
