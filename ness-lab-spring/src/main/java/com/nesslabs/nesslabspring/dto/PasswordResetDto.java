package com.nesslabs.nesslabspring.dto;

import lombok.*;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class PasswordResetDto {
    private String newPassword;
}
