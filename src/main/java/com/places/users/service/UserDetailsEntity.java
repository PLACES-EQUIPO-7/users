package com.places.users.service;

import com.places.users.utils.enums.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import javax.management.relation.Role;
import java.util.Collection;

@Getter
@Setter
public class UserDetailsEntity extends User {

    private String userId;

    private UserRole role;


    public UserDetailsEntity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetailsEntity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserDetailsEntity(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId, UserRole role) {
        super(username, password, authorities);
        this.userId = userId;
        this.role = role;
    }

    public UserDetailsEntity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userId, UserRole role) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.role = role;
    }
}
