package com.github.daka7a922.eventsphere.event.service;

import com.github.daka7a922.eventsphere.client.NotificationServiceClient;
import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.model.EventType;
import com.github.daka7a922.eventsphere.event.repository.EventRepository;
import com.github.daka7a922.eventsphere.web.dto.CreateEventRequest;
import com.github.daka7a922.eventsphere.web.dto.NotificationLogDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EventService {

    private final EventRepository eventRepository;
    private final NotificationServiceClient notificationClient;

    @Autowired
    public EventService(EventRepository eventRepository, NotificationServiceClient notificationClient) {
        this.eventRepository = eventRepository;
        this.notificationClient = notificationClient;
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

        eventRepository.save(event);

        NotificationLogDto logData = new NotificationLogDto();

        logData.setMessage("New event created: " + event.getName());
        logData.setRecipient("admin@eventsphere.com");
        logData.setEventReference("Event id: " + event.getId());
        logData.setTimestamp(LocalDateTime.now());

        notificationClient.sendNotification(logData)
                .subscribe( // Subscribe to trigger the call and handle the response/error
                        response -> System.out.println("Notification log sent successfully. Response ID: " + (response != null ? response.getId() : "N/A")),
                        error -> System.err.println("Failed to send notification log: " + error.getMessage())
                );





        return event;


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

        return eventRepository.findAll().stream().filter(Event::isFeatured).collect(Collectors.toList());
    }


    public void retrieveNotifications() {
        System.out.println("Retrieving all notifications from NotificationService...");
        notificationClient.getAllNotifications()
                .collectList() // Gather all results into a List
                .subscribe(
                        notificationList -> {
                            System.out.println("Retrieved " + notificationList.size() + " notification logs:");
                            notificationList.forEach(log -> System.out.println(" - ID: " + log.getId() + ", Msg: " + log.getMessage()));
                        },
                        error -> System.err.println("Failed to retrieve notifications: " + error.getMessage())
                );
    }


}
