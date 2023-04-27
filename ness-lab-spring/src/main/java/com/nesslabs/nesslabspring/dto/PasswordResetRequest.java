package com.nesslabs.nesslabspring.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PasswordResetRequest {
    private String email;
    private String oldPassword;
//    private String token;
    private String newPassword;
//    private String confirmPassword;
}
