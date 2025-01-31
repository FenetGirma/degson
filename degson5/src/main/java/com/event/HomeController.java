package com.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private JavaMailSender mailSender;

    @GetMapping("/")
    public String home() {
        return "index"; // This matches index.html in the templates directory
    }
    @GetMapping("/signup.html")
public String signup() {
    return "signup"; // This matches signup.html in the templates directory
}
@GetMapping("/signupAdmin.html")
public String signupAdmin() {
    return "signupAdmin"; // This matches signup.html in the templates directory
}
@GetMapping("/login.html")
public String login() {
    return "login"; // This matches signup.html in the templates directory
}
@GetMapping("/events.html")
public String events() {
    return "events"; // This matches signup.html in the templates directory
}
@GetMapping("/eventsUser.html")
public String eventsUser() {
    return "eventsUser"; // This matches signup.html in the templates directory
}
@GetMapping("/schedule.html")
    public String schedule() {
        return "schedule";
    }

    
@GetMapping("/tickets.html")
public String tickets() {
    return "tickets"; // This matches signup.html in the templates directory
}

@GetMapping("/create-event.html")
public String createvent() {
    return "create-event"; // This matches signup.html in the templates directory
}
@GetMapping("/attendee.html")
public String attendee() {
    return "attendee"; // This matches signup.html in the templates directory
}

@GetMapping("/contact.html")
    public String contact() {
        return "contact";
    }

    @PostMapping("/contact/send")
    @ResponseBody
    public String sendEmail(@RequestParam String name,
                            @RequestParam String email,
                            @RequestParam String message) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom("eaddemo20@gmail.com");
            mailMessage.setTo("lidyagebretsadikw@gmail.com"); // Send to admin email
            mailMessage.setSubject("New Contact Us Message from " + name);
            mailMessage.setText("Email: " + email + "\n\nMessage:\n" + message);

            mailSender.send(mailMessage);
            return "Email sent successfully!";
        } catch (Exception e) {
            return "Error sending email: " + e.getMessage();
        }
    }

}

