package com.nesslabs.nesslabspring.repositories;

public interface EmailSender {
    void send(String to, String email);

}
