package com.github.daka7a922.eventsphere.venue.service;

import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.model.VenueType;
import com.github.daka7a922.eventsphere.web.dto.VenueRegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class VenueInit implements CommandLineRunner {

    private final VenueService venueService;

    @Autowired
    public VenueInit(VenueService venueService) {
        this.venueService = venueService;
    }


    @Override
    public void run(String... args) throws Exception {


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


    }

}
