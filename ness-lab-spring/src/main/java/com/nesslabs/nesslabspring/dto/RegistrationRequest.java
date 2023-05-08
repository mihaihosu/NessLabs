package com.nesslabs.nesslabspring.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class RegistrationRequest {
    private final  String email;
    private final String username;
    private final String password;
    private final boolean is_admin;
}
