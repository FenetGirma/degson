package main.java.com.event.email;

import main.java.com.event.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "*") // Enable CORS if needed
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public String sendEmail(@RequestParam String email, 
                            @RequestParam String subject, 
                            @RequestParam String message) {
        emailService.sendEmail(email, subject, message);
        return "Email sent successfully!";
    }
}
