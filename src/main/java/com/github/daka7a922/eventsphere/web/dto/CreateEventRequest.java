package com.github.daka7a922.eventsphere.web.dto;

import com.github.daka7a922.eventsphere.event.model.EventType;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRequest {

    @Size(min = 1, max = 50, message = "Title must be between 1 and 50 symbols")
    private String name;

    @URL
    @NotNull
    private String eventPictureURL;

    @Size(max = 1000, message = "Description can be maximum 1000 symbols")
    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime dateAndTime;

    @PositiveOrZero
    @NotNull
    private BigDecimal price;

    private EventType eventType;

    @NotNull
    @Positive
    private Integer availableTickets;

    @NotNull
    private Venue venue;



}
