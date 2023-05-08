package com.nesslabs.nesslabspring.dto;


import lombok.*;


//Changed cuz error in postman
//Cannot construct instance of `com.nesslabs.nesslabspring.dto.RegistrationRequest`
// (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationRequest {
    private  String email;
    private  String username;
    private  String password;
    private  boolean is_admin;
}
