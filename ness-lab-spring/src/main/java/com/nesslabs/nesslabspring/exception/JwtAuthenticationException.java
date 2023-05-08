package com.nesslabs.nesslabspring.exception;

public class JwtAuthenticationException extends RuntimeException{
    public JwtAuthenticationException(final String ex){
        super(ex);
    }
}
