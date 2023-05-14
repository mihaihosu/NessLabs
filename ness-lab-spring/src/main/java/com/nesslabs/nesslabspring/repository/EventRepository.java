package com.nesslabs.nesslabspring.repository;

import com.nesslabs.nesslabspring.model.Event;
import com.nesslabs.nesslabspring.model.PasswordResetToken;
import com.nesslabs.nesslabspring.model.Tag;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event,Long> {
    Event findEventById(Long id);
}
