package com.nesslabs.nesslabspring.model;


import com.nesslabs.nesslabspring.enums.EventStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="_event")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String photo;

    private String title;

    private String description;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private String address;

    private String eventLink;

    private String ticketLink;

    private Boolean isFree;

    private Boolean isPetFriendly;

    private Boolean isKidFriendly;

    private String adminEmail;

    private String tagName;

    private EventStatus eventStatus;
}
