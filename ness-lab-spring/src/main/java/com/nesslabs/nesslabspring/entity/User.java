package com.nesslabs.nesslabspring.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String username;
    private String password;
    boolean is_admin;
    boolean is_confirmed = false;

    public User(String username, String email, String password, boolean is_admin) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.is_admin = is_admin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleName = is_admin ? "ADMIN" : "USER";
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(roleName);
        return Collections.singletonList(authority);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !is_confirmed;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return is_confirmed;
    }

    public void setIs_confirmed(boolean is_confirmed) {
        this.is_confirmed = is_confirmed;
    }
}
