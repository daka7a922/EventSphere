package com.github.daka7a922.eventsphere.event.service;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.model.EventType;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.service.VenueService;
import com.github.daka7a922.eventsphere.web.dto.CreateEventRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class EventInit implements CommandLineRunner {


    private final VenueService venueService;
    private final EventService eventService;

    public EventInit(VenueService venueService, EventService eventService) {
        this.venueService = venueService;
        this.eventService = eventService;
    }

    @Override
    public void run(String... args) throws Exception {

        List<Venue> venues = venueService.getAllVenues();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");


        if (!eventService.getAllEvents().isEmpty()){
            return;
        }

        CreateEventRequest eventRequest1 = CreateEventRequest.builder()
                .name("Matteo Bocelli")
                .description("След турнето си в Северна и Южна Америка, Матео Бочели се отправя на ново, още по-голямо европейско турне, което включва и ексклузивна дата за България - на 11.06.2025 г., на любимата за всички звезди локация - Античен театър – Пловдив.\n" +
                        "\n" +
                        "Предварителната продажба на билети за членовете на фен-клуба започва на 19.11.2024г. в 10:00 часа.\n" +
                        "\n" +
                        "Билетите ще бъдат в масова продажба от 10:00 часа на 22.11.2024 г.")
                .dateAndTime(LocalDateTime.parse("22-11-2025 10:00", formatter))
                .eventType(EventType.CONCERT)
                .price(BigDecimal.valueOf(119.99))
                .availableTickets(399)
                .venue(venues.get(1))
                .eventPictureURL("https://bg.content.eventim.com/static/uploaded/bg/1/p/n/u/1pnu_960_360.webp")
                .build();

        Event event1 = eventService.createEvent(eventRequest1);
        eventService.changeEventFeatured(event1, true);


        CreateEventRequest eventRequest2 = CreateEventRequest.builder()
                .name("Disco music fest - Back to the 80‘ and 90")
                .description("Disco Music Fest: Back to the '80s ('90s) е сред новите фестивали в Културния календар на Пловдив, но още с първото си издание през 2023г. буквално „взриви“ публиката в две поредни вечери на Античния театър с любимите хитове на световно известния дует LA BOUCHE и невероятните KOOL & THE GANG. Въпреки проливния дъжд, нищо не попречи на възторжената атмосфера, в която хиляди пловдивчани и гости от различни поколения се потопиха, пеейки „Cherish” заедно с легендарните музиканти, наслаждавайки се на уникално шоу и звук.")
                .dateAndTime(LocalDateTime.parse("15-06-2025 18:00", formatter))
                .eventType(EventType.FESTIVAL)
                .price(BigDecimal.valueOf(70))
                .availableTickets(2500)
                .venue(venues.get(2))
                .eventPictureURL("https://bg.content.eventim.com/static/uploaded/bg/z/i/y/x/ziyx_960_360.webp")
                .build();

        Event event2 = eventService.createEvent(eventRequest2);
        eventService.changeEventFeatured(event2, true);



        CreateEventRequest eventRequest3 = CreateEventRequest.builder()
                .name("Exclusive Celebrate Queen Sensation ")
                .description("Не пропускайте истинска СЕНЗАЦИЯ в света на музиката! Само на 7 Април | 20:00 | Pirotska 5 Event Centre! Exclusive Celebrate Party 40 Years Anniversary “I was born to Love you”!\n" +
                        "\n")
                .dateAndTime(LocalDateTime.parse("15-04-2025 19:00", formatter))
                .eventType(EventType.CONCERT)
                .price(BigDecimal.valueOf(40))
                .availableTickets(250)
                .venue(venues.get(1))
                .eventPictureURL("https://bg.content.eventim.com/static/uploaded/bg/5/n/g/e/5nge_960_360.webp")
                .build();

        Event event3 = eventService.createEvent(eventRequest3);
        eventService.changeEventFeatured(event3, true);



    }
}

