package com.places.users.utils.mappers;

import com.places.users.DTOs.CreateUserDTO;
import com.places.users.DTOs.UserDTO;
import com.places.users.model.PlacesInfo;
import com.places.users.model.UserEntity;
import com.places.users.utils.enums.PlaceRole;
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
                .placesInfo(createUserDTO.getPlacesInfo())
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
                .placesInfo(user.getPlacesInfo())
                .build();
    }

    public static List<Map<String, String>> buildPlaceUserRelationsClaim(List<PlacesInfo.PlaceUserRelation> placeUserRelations) {
        return placeUserRelations
                .stream()
                .map(e -> Map.of(e.getId(), e.getRole().getValue()))
                .toList();
    }

    public static PlacesInfo buildPlacesInfoFromClaim(List<Map<String, String>> placeUserRelations) {
        List<PlacesInfo.PlaceUserRelation> placeUserRelations1 = placeUserRelations
                .stream()
                .flatMap(e -> e.entrySet().stream().map(entry -> new PlacesInfo.PlaceUserRelation(entry.getKey(), PlaceRole.fromValue(entry.getValue()))))
                .toList();

        return new PlacesInfo(placeUserRelations1);
    }
}
