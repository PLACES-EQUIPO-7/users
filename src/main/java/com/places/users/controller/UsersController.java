package com.places.users.controller;

import com.places.users.DTOs.ChangePasswordDTO;
import com.places.users.DTOs.CreateUserDTO;
import com.places.users.DTOs.UserDTO;
import com.places.users.DTOs.UserIdsDTO;
import com.places.users.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UsersService usersService;

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping(value = "/create")
    public ResponseEntity<UserDTO> createUser(@RequestBody CreateUserDTO createUserDTO) {

        UserDTO userDTO = usersService.createUser(createUserDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {

        usersService.updateUser(userDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping()
    public ResponseEntity<UserDTO> getUserById(@RequestParam(value = "user_id") String userId) {

        UserDTO userDTO = usersService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }


    @GetMapping(value = "/by/dni/{dni}")
    public ResponseEntity<UserDTO> getUserByDNI(@PathVariable(value = "dni") String dni) {

        UserDTO userDTO = usersService.getUserByDNI(dni);

        return ResponseEntity.status(HttpStatus.OK).body(userDTO);
    }

    @PostMapping("/change/password")
    public ResponseEntity<Void> post(@Valid @RequestBody ChangePasswordDTO changePasswordDTO) {

        usersService.changePassword(changePasswordDTO);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @PostMapping(value = "/get/users/by/ids")
    public ResponseEntity<List<UserDTO>> getUserBYEmail(@Valid @RequestBody UserIdsDTO ids) {

        List<UserDTO> userDTOs = usersService.getUsersByIds(ids.getUserIds());

        return ResponseEntity.status(HttpStatus.OK).body(userDTOs);
    }

}
