package com.github.daka7a922.eventsphere.web;

import com.github.daka7a922.eventsphere.event.model.Event;
import com.github.daka7a922.eventsphere.event.service.EventService;
import com.github.daka7a922.eventsphere.security.AuthenticationDetails;
import com.github.daka7a922.eventsphere.user.model.User;
import com.github.daka7a922.eventsphere.user.service.UserService;
import com.github.daka7a922.eventsphere.web.dto.LoginRequest;
import com.github.daka7a922.eventsphere.web.dto.RegisterRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private final UserService userService;
    private final EventService eventService;

    @Autowired
    public IndexController(UserService userService, EventService eventService) {
        this.userService = userService;
        this.eventService = eventService;
    }

    @GetMapping
    public ModelAndView getIndexPage() {

        List<Event> featuredEvents = eventService.getFeaturedEvents();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("featuredEvents", featuredEvents);

        return modelAndView;
    }

    @GetMapping("/register")
    public ModelAndView getRegisterPage() {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("registerRequest", new RegisterRequest());
        modelAndView.setViewName("register");

        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView registerNewUser(@Valid RegisterRequest registerRequest, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("register");
        }

        userService.register(registerRequest);

        return new ModelAndView("redirect:/login");
    }


    @GetMapping("/login")
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String errorParam) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        modelAndView.addObject("loginRequest", new LoginRequest());

        if (errorParam != null) {
            modelAndView.addObject("errorMessage", "Incorrect username or password!");
        }

        return modelAndView;

    }

    @GetMapping("/dashboard")
    public ModelAndView getHomePage(@AuthenticationPrincipal AuthenticationDetails userDetails) {

        User user = userService.getByUsername(userDetails.getUsername());

        List<Event> savedEvents = user.getSavedEvents();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dashboard");
        modelAndView.addObject("user", user);
        modelAndView.addObject("savedEvents", savedEvents);
        modelAndView.addObject("activePage", "dashboard");

        return modelAndView;
    }



    // TESTING HTMLs //

    @GetMapping("1")
    public String index1() {
        return "create-event";
    }
    @GetMapping("2")
    public String index2() {
        return "event-details";
    }
    @GetMapping("3")
    public String index3() {
        return "login";
    }
    @GetMapping("4")
    public String index4() {
        return "ticket-purchaseV2";
    }
    @GetMapping("5")
    public String index5() {
        return "dashboard";
    }

    @GetMapping("6")
    public String index6() {
        return "organizer-analytics";
    }

}
