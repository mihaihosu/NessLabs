package com.nesslabs.nesslabspring.utils.exception;

public class JwtAuthenticationException extends RuntimeException{
    public JwtAuthenticationException(final String ex){
        super(ex);
    }
}
