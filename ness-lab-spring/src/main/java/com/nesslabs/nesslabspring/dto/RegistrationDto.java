package com.nesslabs.nesslabspring.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private String email;
    private String username;
    private String password;
    private boolean admin;
}
