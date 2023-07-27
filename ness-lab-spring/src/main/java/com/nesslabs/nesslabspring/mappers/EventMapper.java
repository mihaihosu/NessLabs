package com.nesslabs.nesslabspring.mappers;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.enums.EventStatus;
import com.nesslabs.nesslabspring.model.Event;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class EventMapper {
    public EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .photo(event.getPhoto())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDate(event.getStartDateTime().toLocalDate())
                .endDate(event.getEndDateTime().toLocalDate())
                .startTime(event.getStartDateTime().toLocalTime())
                .duration(Duration.between(event.getEndDateTime().toLocalTime(), (event.getStartDateTime().toLocalTime())))
                .address(event.getAddress())
                .eventLink(event.getEventLink())
                .ticketLink(event.getTicketLink())
                .isFree(event.getIsFree())
                .isPetFriendly(event.getIsPetFriendly())
                .isKidFriendly(event.getIsKidFriendly())
                .adminEmail(event.getAdminEmail())
                .tagName(event.getTagName())
                .eventStatus(event.getEventStatus())
                .build();
    }

    public Event fromDto(EventDto eventDto) {
        Event.EventBuilder eventBuilder = Event.builder()
                .id(eventDto.getId())
                .photo(eventDto.getPhoto())
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .address(eventDto.getAddress())
                .eventLink(eventDto.getEventLink())
                .ticketLink(eventDto.getTicketLink())
                .isFree(eventDto.getIsFree())
                .isPetFriendly(eventDto.getIsPetFriendly())
                .isKidFriendly(eventDto.getIsKidFriendly())
                .adminEmail(eventDto.getAdminEmail())
                .tagName(eventDto.getTagName());

        if(eventDto.getStartDate()!=null) {
            if(eventDto.getStartTime() !=null) {
                eventBuilder.startDateTime(LocalDateTime.of(eventDto.getStartDate(), eventDto.getStartTime()));
            } else {
                eventBuilder.startDateTime(LocalDateTime.of(eventDto.getStartDate(), LocalTime.of(12,0)));
            }
        } else {
            eventBuilder.startDateTime(null);
        }

        if(eventDto.getEndDate() != null) {
            if(eventDto.getStartTime() != null && eventDto.getDuration() != null) {
                eventBuilder.endDateTime(LocalDateTime.of(eventDto.getEndDate(), eventDto.getStartTime().plus(eventDto.getDuration())));
            } else {
                eventBuilder.endDateTime(LocalDateTime.of(eventDto.getEndDate(), LocalTime.of(12,0)));
            }
        } else {
            eventBuilder.endDateTime(null);
        }

        // If event status is not provided in the DTO, set it as DRAFT
        if (eventDto.getEventStatus() != null) {
            eventBuilder.eventStatus(eventDto.getEventStatus());
        } else {
            eventBuilder.eventStatus(EventStatus.DRAFT);
        }

        return eventBuilder.build();
    }

}
