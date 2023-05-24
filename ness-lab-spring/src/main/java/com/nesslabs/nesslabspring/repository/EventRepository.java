package com.nesslabs.nesslabspring.repository;

import com.nesslabs.nesslabspring.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

}
