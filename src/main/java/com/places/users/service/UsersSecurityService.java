package com.places.users.service;

import com.places.users.model.UserEntity;
import com.places.users.repository.mongo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersSecurityService implements UserDetailsService {

    private final UserRepository userRepository;

    public UsersSecurityService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity user = userRepository.findByUserName(username);

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();


        return new UserDetailsEntity
                (
                        username,
                        user.getPassword(),
                        true,
                        true,
                        true,
                        true,
                        grantedAuthorities,
                        user.getId(),
                        user.getRole()
                );
    }
}
