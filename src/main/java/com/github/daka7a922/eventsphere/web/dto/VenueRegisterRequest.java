package com.github.daka7a922.eventsphere.web.dto;

import com.github.daka7a922.eventsphere.venue.model.VenueType;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VenueRegisterRequest {

    private String name;

    private String address;

    private String city;

    private String country;

    private String description;

    private VenueType type;

}
