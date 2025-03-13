package com.places.users.service;

import com.places.users.model.PlacesInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Getter
@Setter
public class UserDetailsEntity extends User {

    private String userId;

    private PlacesInfo placesInfo;


    public UserDetailsEntity(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public UserDetailsEntity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }

    public UserDetailsEntity(String username, String password, Collection<? extends GrantedAuthority> authorities, String userId, PlacesInfo placesInfo) {
        super(username, password, authorities);
        this.userId = userId;
        this.placesInfo = placesInfo;
    }

    public UserDetailsEntity(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String userId, PlacesInfo placesInfo) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.userId = userId;
        this.placesInfo = placesInfo;
    }
}
