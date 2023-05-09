package com.nesslabs.nesslabspring.dto;


import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String username;
    private String password;
    private boolean is_admin;
}
