package com.github.daka7a922.eventsphere.event.service;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.repository.EventRepository;
import com.github.daka7a922.eventsphere.web.dto.CreateEventRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EventService {

    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }


    public Event createEvent(CreateEventRequest createEventRequest) {


        Event event = Event.builder()
                .name(createEventRequest.getName())
                .description(createEventRequest.getDescription())
                .dateAndTime(createEventRequest.getDateAndTime())
                .eventPicture(createEventRequest.getEventPictureURL())
                .eventType(createEventRequest.getEventType())
                .availableTickets(createEventRequest.getAvailableTickets())
                .venue(createEventRequest.getVenue())
                .build();


        return eventRepository.save(event);


    }

    public Event getById(UUID id) {

        return eventRepository.getEventById(id);
    }
}
