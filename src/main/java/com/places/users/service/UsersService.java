package com.places.users.service;

import com.places.users.DTOs.ChangePasswordDTO;
import com.places.users.DTOs.CreateUserDTO;
import com.places.users.DTOs.UserDTO;
import com.places.users.exceptions.DataNotFoundException;
import com.places.users.exceptions.UnAuthorizedException;
import com.places.users.model.UserEntity;
import com.places.users.repository.mongo.UserRepository;
import com.places.users.utils.Constants;
import com.places.users.utils.enums.ErrorCode;
import com.places.users.utils.mappers.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.places.users.utils.mappers.UserMapper.buildUserDTOFromUser;

@Service
@Slf4j
public class UsersService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    public UsersService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(CreateUserDTO createUserDTO) {

        UserEntity createUser = UserMapper.buildUserFromCreateDTO(createUserDTO);
        createUser.setPassword(passwordEncoder.encode(createUserDTO.getPassword()));

        UserEntity UserCreated = userRepository.save(createUser);

        return buildUserDTOFromUser(UserCreated);

    }

    public void updateUser(UserDTO userDTO) {

        Query query = new Query(Criteria.where("_id").is(userDTO.getId()));
        Update update = buildUpdate(userDTO);

        userRepository.update(query, update);

    }

    public UserDTO getUserById(String id) {


        UserEntity user = userRepository.findById(id);

        if(Objects.isNull(user)) {
            throw new DataNotFoundException("User not found", ErrorCode.USER_NOT_FOUND);
        }

        return buildUserDTOFromUser(user);
    }

    public UserDTO getUserByDNI(String id) {

        UserEntity user = userRepository.findByDNI(id);

        if(Objects.isNull(user)) {
            throw new DataNotFoundException("User not found", ErrorCode.USER_NOT_FOUND);
        }

        return buildUserDTOFromUser(user);
    }

    public UserDTO getUserBYEmail(String email) {
        
        UserEntity user = userRepository.findByEmail(email);

        if(Objects.isNull(user)) {
            throw new DataNotFoundException("User not found", ErrorCode.USER_NOT_FOUND);
        }

        return buildUserDTOFromUser(user);
    }

    private Update buildUpdate(UserDTO userDTO) {
        Update update = new Update();

        update.set(Constants.USER.DNI, userDTO.getDNI());
        update.set(Constants.USER.DNI_TYPE, userDTO.getDNIType());
        update.set(Constants.USER.EMAIL, userDTO.getEmail());
        update.set(Constants.USER.FIRST_NAME, userDTO.getFirstName());
        update.set(Constants.USER.LAST_NAME, userDTO.getLastName());

        return update;
    }


    public void changePassword(ChangePasswordDTO changePasswordDTO) {

        UserEntity user = userRepository.findById(changePasswordDTO.getUserId());

        if (user == null) {
            throw new DataNotFoundException("User not found", ErrorCode.USER_NOT_FOUND);
        }


        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new UnAuthorizedException("Old password is incorrect", ErrorCode.UNAUTHORIZED);
        }

        Query query = new Query(Criteria.where("_id").is(user.getId()));
        Update update = new Update();
        update.set("password", passwordEncoder.encode(changePasswordDTO.getNewPassword()));

        userRepository.update(query, update);

    }

    public List<UserDTO> getUsersByIds(List<String> ids) {

        List<UserEntity> users = userRepository.findByIds(ids);

        return users.stream().map(UserMapper::buildUserDTOFromUser).toList();
    }
}
