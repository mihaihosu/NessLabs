package com.nesslabs.nesslabspring.repository;

public interface EmailSender {
    void send(String to, String email);

}
