package com.github.daka7a922.eventsphere.venue.service;

import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.repository.UserRepository;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.model.VenueType;
import com.github.daka7a922.eventsphere.web.dto.RegisterRequest;
import com.github.daka7a922.eventsphere.web.dto.VenueRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VenueInit implements CommandLineRunner {

    private final VenueService venueService;
    private final UserRepository userRepository;
    private final UserService userService;

    @Autowired
    public VenueInit(VenueService venueService, UserRepository userRepository, UserService userService) {
        this.venueService = venueService;
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @Override
    public void run(String... args) throws Exception {

        //Initializing venues manually, because venues will only be added by validated organizers//

        if (!venueService.getAllVenues().isEmpty()) {
            return;
        }

        VenueRegisterRequest venue1 = VenueRegisterRequest.builder()
                .name("Dock 5")
                .address("DOCK 5 Night Club, Morska gara Burgas, Harbour complex, Magaziq 1, 8000 Burgas")
                .city("Burgas")
                .country("Bulgaria")
                .description("Beach club")
                .capacity(700)
                .type(VenueType.CLUB)
                .build();

        venueService.registerVenue(venue1);

        VenueRegisterRequest venue2 = VenueRegisterRequest.builder()
                .name("Vasil Levski Stadium")
                .address("Bulgaria Blvd")
                .city("Sofia")
                .country("Bulgaria")
                .description("Evlogi i Hristo Georgiev Blvd 38, 1164 Sofia")
                .capacity(43230)
                .type(VenueType.STADIUM)
                .build();

        venueService.registerVenue(venue2);

        VenueRegisterRequest venue3 = VenueRegisterRequest.builder()
                .name("Arena 8888")
                .address("Blvd Asen Yordanov 1, 1113 Sofia")
                .city("Sofia")
                .country("Bulgaria")
                .description("A multi-purpose indoor arena")
                .capacity(12373)
                .type(VenueType.ARENA)
                .build();

        venueService.registerVenue(venue3);

        VenueRegisterRequest venue4 = VenueRegisterRequest.builder()
                .name("Drama theater Plovdiv")
                .address("TsentarPlovdiv Center, ul.Knyaz Alexander I 38, 4000 Plovdiv")
                .city("Plovdiv")
                .country("Bulgaria")
                .description("Plovdiv Drama Theatre is the first professional Bulgarian theatre, founded in 1881, and one of the leading cultural institutions in the country.")
                .capacity(500)
                .type(VenueType.THEATER)
                .build();

        venueService.registerVenue(venue4);

    }

}
