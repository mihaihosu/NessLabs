package com.nesslabs.nesslabspring.repository;
import com.nesslabs.nesslabspring.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface EventRepository extends JpaRepository<Event, Long>, JpaSpecificationExecutor<Event> {

}
