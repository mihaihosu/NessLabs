package com.nesslabs.nesslabspring.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private String email;
    private String username;
    private String password;
    @JsonProperty("isAdmin")
    private boolean is_admin;
}
