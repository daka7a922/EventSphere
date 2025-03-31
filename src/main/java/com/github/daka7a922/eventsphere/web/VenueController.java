package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.venue.model.Venue;
import com.github.daka7a922.eventsphere.venue.service.VenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/venue")
public class VenueController {


    private final UserService userService;
    private final VenueService venueService;

    @Autowired
    public VenueController(UserService userService, VenueService venueService) {
        this.userService = userService;
        this.venueService = venueService;
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
}
