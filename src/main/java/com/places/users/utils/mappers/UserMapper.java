package com.places.users.utils.mappers;

import com.places.users.DTOs.CreateUserDTO;
import com.places.users.DTOs.UserDTO;
import com.places.users.model.UserEntity;
import com.places.users.utils.enums.UserRole;
import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@UtilityClass
public class UserMapper {

    public static UserEntity buildUserFromCreateDTO(CreateUserDTO createUserDTO) {
        return UserEntity.builder()
                .DNI(createUserDTO.getDNI())
                .DNIType(createUserDTO.getDNIType())
                .firstName(createUserDTO.getFirstName())
                .lastName(createUserDTO.getLastName())
                .username(createUserDTO.getUserName())
                .password(createUserDTO.getPassword())
                .email(createUserDTO.getEmail())
                .tel(createUserDTO.getTel())
                .role(createUserDTO.getRole())
                .updatedAt(Instant.now())
                .createdAt(Instant.now())
                .build();
    }

    public static UserDTO buildUserDTOFromUser(UserEntity user){
        return  UserDTO.builder()
                .id(user.getId())
                .DNI(user.getDNI())
                .DNIType(user.getDNIType())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userName(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

}
