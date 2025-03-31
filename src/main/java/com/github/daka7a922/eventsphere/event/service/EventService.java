package com.github.daka7a922.eventsphere.event.service;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.model.EventType;
import com.github.daka7a922.eventsphere.event.repository.EventRepository;
import com.github.daka7a922.eventsphere.web.dto.CreateEventRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .price(createEventRequest.getPrice())
                .availableTickets(createEventRequest.getAvailableTickets())
                .venue(createEventRequest.getVenue())
                .isFeatured(false)
                .build();


        return eventRepository.save(event);


    }

    public Event getById(UUID id) {

        return eventRepository.getEventById(id);
    }

    public List<Event> getEventsByType(EventType type) {

        return eventRepository.findByEventType(type);
    }

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public void changeEventFeatured(Event event, boolean isFeatured){

        event.setFeatured(isFeatured);
        eventRepository.save(event);

    }

    public List<Event> getFeaturedEvents(){

        return eventRepository.findAll().stream().filter(event -> event.isFeatured()).collect(Collectors.toList());
    }
}
