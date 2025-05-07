package com.places.users.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.places.users.utils.Constants;
import com.places.users.utils.enums.DNIType;
import com.places.users.utils.enums.UserRole;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "user")
@CompoundIndexes({
    @CompoundIndex(name = "dni_type_idx", 
                   def = "{'dni' : 1, 'dni_type': 1}",
                   unique = true)
})
public class UserEntity {

    @Id
    private String id;

    @Field(Constants.USER.DNI)
    private String DNI;

    @Field(Constants.USER.DNI_TYPE)
    private DNIType DNIType;

    @Field(Constants.USER.FIRST_NAME)
    private String firstName;

    @Field(Constants.USER.LAST_NAME)
    private String lastName;

    @Field(Constants.USER.USERNAME)
    private String username;

    private String password;

    @Field(Constants.USER.EMAIL)
    private String email;

    @Field(Constants.USER.TEL)
    private String tel;

    @Field(Constants.USER.ROLE)
    private UserRole role;

    @Field(Constants.USER.CREATED_AT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant createdAt;

    @Field(Constants.USER.UPDATED_AT)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private Instant updatedAt;

}