package com.nesslabs.nesslabspring.dto;

import lombok.*;


//probably no longer needed
@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PasswordResetRequest {
    private String token;
    private String newPassword;
}
