package com.nesslabs.nesslabspring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nesslabs.nesslabspring.enums.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDate;
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

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime startTime;

    private Duration duration;

    private String address;

    private String eventLink;

    private String ticketLink;

    @JsonProperty("isFree")
    private boolean isFree;

    @JsonProperty("isPetFriendly")
    private boolean isPetFriendly;

    @JsonProperty("isKidFriendly")
    private boolean isKidFriendly;

    private String adminEmail;

    private String tagName;

    private EventStatus eventStatus;

}