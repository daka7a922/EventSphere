package com.github.daka7a922.eventsphere.venue.service;

import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.model.VenueType;
import com.github.daka7a922.eventsphere.web.dto.VenueRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VenueInit implements CommandLineRunner {

    private final VenueService venueService;

    @Autowired
    public VenueInit(VenueService venueService) {
        this.venueService = venueService;
    }


    @Override
    public void run(String... args) throws Exception {

        //Initializing venues manually, because venues will only be added by validated organizers//

        if (!venueService.getAllVenues().isEmpty()) {
            return;
        }

        VenueRegisterRequest venue1 = VenueRegisterRequest.builder()
                .name("Gold Club")
                .address("Gold street 1")
                .city("Burgas")
                .country("Bulgaria")
                .description("Beach club")
                .type(VenueType.CLUB)
                .build();

        venueService.registerVenue(venue1);

        VenueRegisterRequest venue2 = VenueRegisterRequest.builder()
                .name("Vasil Levski Stadium")
                .address("Bulgaria Blvd")
                .city("Sofia")
                .country("Bulgaria")
                .description("Vasil Levski National Stadium")
                .type(VenueType.STADIUM)
                .build();

        venueService.registerVenue(venue2);

        VenueRegisterRequest venue3 = VenueRegisterRequest.builder()
                .name("Arena 8888")
                .address("Asen Yordanov Blvd")
                .city("Sofia")
                .country("Bulgaria")
                .description("A multi-purpose indoor arena")
                .type(VenueType.ARENA)
                .build();

        venueService.registerVenue(venue3);

        VenueRegisterRequest venue4 = VenueRegisterRequest.builder()
                .name("Drama theater Plovdiv")
                .address("High street")
                .city("Plovdiv")
                .country("Bulgaria")
                .description("Plovdiv Drama Theatre is the first professional Bulgarian theatre, founded in 1881, and one of the leading cultural institutions in the country.")
                .type(VenueType.THEATER)
                .build();

        venueService.registerVenue(venue4);



    }

}
