package com.nesslabs.nesslabspring.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String username;
    private String password;
    private boolean is_admin;
}
