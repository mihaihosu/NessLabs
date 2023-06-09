package com.nesslabs.nesslabspring.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDto {
    private String email;
    private Boolean isAdmin;
    private Boolean isConfirmed;
}
