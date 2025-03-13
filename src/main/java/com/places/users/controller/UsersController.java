package com.places.users.controller;

import com.places.users.DTOs.CreateUserDTO;
import com.places.users.DTOs.UserDTO;
import com.places.users.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //TODO validar usuario admin
    @GetMapping()
    public ResponseEntity<UserDTO> getUserById(@RequestParam(value = "user_id") String userId) {

        UserDTO userDTO = usersService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }
}
