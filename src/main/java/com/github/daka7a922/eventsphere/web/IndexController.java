package com.github.daka7a922.eventsphere.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @GetMapping
    public String index() {
        return "index";
    }

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
        return "ticket-purchase";
    }
    @GetMapping("5")
    public String index5() {
        return "user-dashboard";
    }


}
