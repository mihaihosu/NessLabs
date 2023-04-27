package com.nesslabs.nesslabspring.dto;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private final  String email;
    private final String username;
    private final String password;
    private final boolean is_admin;
}
