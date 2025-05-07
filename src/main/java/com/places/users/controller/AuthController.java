package com.places.users.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.places.users.DTOs.LoginDTO;
import com.places.users.DTOs.LoginTokenDTO;
import com.places.users.DTOs.UserDTO;
import com.places.users.config.GoogleTokenValidator;
import com.places.users.config.JWTUtil;
import com.places.users.exceptions.DataNotFoundException;
import com.places.users.exceptions.UnAuthorizedException;
import com.places.users.service.UserDetailsEntity;
import com.places.users.service.UsersService;
import com.places.users.utils.enums.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UsersService usersService;

    public AuthController(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UsersService usersService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.usersService = usersService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO) {

        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(login);

        String jwt = this.jwtUtil.create(authentication);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }

    @PostMapping("/login/google")
    public ResponseEntity<Void> loginGoogle(@RequestBody LoginTokenDTO login) {

        GoogleTokenValidator.validToken(login.getToken());

        DecodedJWT decodedJWT = JWT.decode(login.getToken());

        UserDTO userDTO = null;

        try {
            userDTO = usersService.getUserBYEmail(decodedJWT.getClaim("email").asString());
        } catch (DataNotFoundException e) {
            throw new UnAuthorizedException("unauthorized", ErrorCode.UNAUTHORIZED);
        }

        String jwt = this.jwtUtil.create(userDTO);

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
