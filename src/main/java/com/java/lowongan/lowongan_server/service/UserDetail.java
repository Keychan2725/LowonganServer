package com.java.lowongan.lowongan_server.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.lowongan.lowongan_server.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Objects;

public class UserDetail implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;

    private String email;

    private String username;
    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;




    public UserDetail(Long id, String email, String password ,String username) {
        this.id = id;
        this.username = email;
        this.password = password;
        this.username = username;
    }

    public static UserDetail buildUser(User admin) {
        return new UserDetail(
                admin.getId(),
                admin.getEmail(),
                admin.getPassword(),
                admin.getUsername());

    }


    public Long getId() {
        return id;
    }



    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetail admin = (UserDetail) o;
        return Objects.equals(id, admin.id);
    }

}