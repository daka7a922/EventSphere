package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.event.service.EventService;
import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.service.VenueService;
import com.github.daka7a922.eventsphere.web.dto.VenueRegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/venue")
public class VenueController {


    private final UserService userService;
    private final VenueService venueService;
    private final EventService eventService;

    @Autowired
    public VenueController(UserService userService, VenueService venueService, EventService eventService) {
        this.userService = userService;
        this.venueService = venueService;
        this.eventService = eventService;
    }

    @GetMapping("/venue-management")
    public ModelAndView getVenueManagementPage(@AuthenticationPrincipal AuthenticationDetails userDetails) {

        User user = userService.getByUsername(userDetails.getUsername());
        List<Venue> allVenues = venueService.getAllVenues();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("venue-management");
        modelAndView.addObject("user", user);
        modelAndView.addObject("allVenues", allVenues);
        modelAndView.addObject("activePage", "venue-management");


        return modelAndView;
    }

    @GetMapping("/add-venue")
    public ModelAndView getAddVenuePage(@AuthenticationPrincipal AuthenticationDetails userDetails) {

        User user = userService.getByUsername(userDetails.getUsername());


        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-venue");
        modelAndView.addObject("user", user);
        modelAndView.addObject("venueRegisterRequest", VenueRegisterRequest.builder().build());
        modelAndView.addObject("activePage", "venue-management");


        return modelAndView;
    }

    @PostMapping("/add-venue")
    public ModelAndView createNewVenue(@Valid VenueRegisterRequest venueRegisterRequest, BindingResult bindingResult, @AuthenticationPrincipal AuthenticationDetails userDetails) {

       if (bindingResult.hasErrors()) {
           User user = userService.getByUsername(userDetails.getUsername());
           ModelAndView modelAndView = new ModelAndView();
           modelAndView.setViewName("add-venue");
           modelAndView.addObject("user", user);
           modelAndView.addObject("venueRegisterRequest", venueRegisterRequest);
           modelAndView.addObject("activePage", "venue-management");

           return modelAndView;

       }

        venueService.registerVenue(venueRegisterRequest);

       return new ModelAndView("redirect:/venue/venue-management");

    }


}
