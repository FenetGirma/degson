package com.event;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // This matches index.html in the templates directory
    }
    @GetMapping("/signup.html")
public String signup() {
    return "signup"; // This matches signup.html in the templates directory
}
@GetMapping("/login.html")
public String login() {
    return "login"; // This matches signup.html in the templates directory
}
@GetMapping("/events.html")
public String events() {
    return "events"; // This matches signup.html in the templates directory
}


}

