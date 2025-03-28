package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.service.EventService;
import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@Controller
@RequestMapping("tickets")
public class TicketController {

    private final EventService eventService;
    private final UserService userService;

    public TicketController(EventService eventService, UserService userService) {
        this.eventService = eventService;
        this.userService = userService;
    }

    @GetMapping("/ticket-purchase/{id}")
    public ModelAndView purchaseTickets(@PathVariable UUID id, @AuthenticationPrincipal AuthenticationDetails userDetails){

        Event event = eventService.getById(id);
        User user = userService.getByUsername(userDetails.getUsername());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("ticket-purchase");
        modelAndView.addObject("event", event);
        modelAndView.addObject("user", user);

        return modelAndView;
    }
}
