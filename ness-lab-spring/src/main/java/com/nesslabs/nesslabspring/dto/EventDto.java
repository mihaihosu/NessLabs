package com.nesslabs.nesslabspring.dto;

import com.nesslabs.nesslabspring.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;

    private String photo;

    private String title;

    private String description;

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

    private LocalTime startTime;

    private Duration duration;

    private String address;

    private String eventLink;

    private String ticketLink;

    private boolean isFree;

    private boolean isPetFriendly;

    private boolean isKidFriendly;

    private String adminEmail;

    private String tagName;

    private EventStatus status;

}