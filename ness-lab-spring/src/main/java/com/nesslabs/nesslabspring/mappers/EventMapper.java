package com.nesslabs.nesslabspring.mappers;

import com.nesslabs.nesslabspring.dto.EventDto;
import com.nesslabs.nesslabspring.model.Event;
import org.springframework.stereotype.Service;

@Service
public class EventMapper {
    public EventDto toDto(Event event) {
        return EventDto.builder()
                .id(event.getId())
                .photo(event.getPhoto())
                .title(event.getTitle())
                .description(event.getDescription())
                .startDateTime(event.getStartDateTime())
                .endDateTime(event.getEndDateTime())
                .startTime(event.getStartTime())
                .duration(event.getDuration())
                .address(event.getAddress())
                .eventLink(event.getEventLink())
                .ticketLink(event.getTicketLink())
                .isFree(event.isFree())
                .isPetFriendly(event.isPetFriendly())
                .isKidFriendly(event.isKidFriendly())
                .adminEmail(event.getAdminEmail())
                .tagName(event.getTagName())
                .status(event.getStatus())
                .build();
    }

    public Event fromDto(EventDto eventDto) {
        return Event.builder()
                .id(eventDto.getId())
                .photo(eventDto.getPhoto())
                .title(eventDto.getTitle())
                .description(eventDto.getDescription())
                .startDateTime(eventDto.getStartDateTime())
                .endDateTime(eventDto.getEndDateTime())
                .startTime(eventDto.getStartTime())
                .duration(eventDto.getDuration())
                .address(eventDto.getAddress())
                .eventLink(eventDto.getEventLink())
                .ticketLink(eventDto.getTicketLink())
                .isFree(eventDto.isFree())
                .isPetFriendly(eventDto.isPetFriendly())
                .isKidFriendly(eventDto.isKidFriendly())
                .adminEmail(eventDto.getAdminEmail())
                .tagName(eventDto.getTagName())
                .status(eventDto.getStatus())
                .build();
    }

}
