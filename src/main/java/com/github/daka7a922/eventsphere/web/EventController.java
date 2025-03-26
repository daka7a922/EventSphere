package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.service.EventService;
import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.service.VenueService;
import com.github.daka7a922.eventsphere.web.dto.CreateEventRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/events")
public class EventController {

    private final UserService userService;

    private final VenueService venueService;
    private final EventService eventService;

    @Autowired
    public EventController(UserService userService, VenueService venueService, EventService eventService) {
        this.userService = userService;
        this.venueService = venueService;
        this.eventService = eventService;
    }

    @GetMapping("/all-events")
    public String getAllEvents() {

        return "all-events";
    }

    @GetMapping("/create-event")
    public ModelAndView getCreateEvent(@AuthenticationPrincipal AuthenticationDetails userDetails) {

        User user = userService.getByUsername(userDetails.getUsername());
        List<Venue> allVenues = venueService.getAllVenues();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("createEventRequest", CreateEventRequest.builder().build());
        modelAndView.addObject("user", user);
        modelAndView.addObject("allVenues", allVenues);
        modelAndView.setViewName("create-event");

        return modelAndView;
    }


    @PostMapping("/create-event")
    public ModelAndView createNewEvent(@AuthenticationPrincipal AuthenticationDetails userDetails, @Valid CreateEventRequest createEventRequest , BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            User user = userService.getByUsername(userDetails.getUsername());
            List<Venue> allVenues = venueService.getAllVenues();
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("create-event");
            modelAndView.addObject("user", user);
            modelAndView.addObject("allVenues", allVenues);
            modelAndView.addObject("createEventRequest", createEventRequest);
        }

        Event event = eventService.createEvent(createEventRequest);



        return new ModelAndView("redirect:/events/event-details/" + event.getId());
    }

    @GetMapping("/event-details/{id}")
    public ModelAndView getEventDetails( @AuthenticationPrincipal AuthenticationDetails userDetails, @PathVariable UUID id) {

        Event event = eventService.getById(id);
        User user = userService.getByUsername(userDetails.getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("event-details");
        modelAndView.addObject("event", event);
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
