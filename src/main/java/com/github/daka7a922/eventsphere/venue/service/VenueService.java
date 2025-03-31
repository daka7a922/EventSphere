package com.github.daka7a922.eventsphere.venue.service;

import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.repository.VenueRepository;
import com.github.daka7a922.eventsphere.web.dto.VenueRegisterRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class VenueService {

    private final VenueRepository venueRepository;


    @Autowired
    public VenueService(VenueRepository venueRepository) {
        this.venueRepository = venueRepository;
    }


    public List<Venue> getAllVenues() {

        return venueRepository.findAll();
    }

    public void registerVenue(VenueRegisterRequest venueRegisterRequest) {

        Venue venue = Venue.builder()
                .name(venueRegisterRequest.getName())
                .address(venueRegisterRequest.getAddress())
                .city(venueRegisterRequest.getCity())
                .country(venueRegisterRequest.getCountry())
                .description(venueRegisterRequest.getDescription())
                .type(venueRegisterRequest.getType())
                .capacity(venueRegisterRequest.getCapacity())
                .build();

        venueRepository.save(venue);
        log.info("Venue registered: " + venue);

    }
}
