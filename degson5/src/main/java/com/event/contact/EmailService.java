package com.event.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(String senderEmail, String subject, String message) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("eaddemo20@gmail.com"); // Your support email
        email.setSubject("Contact Us Form: " + subject);
        email.setText("From: " + senderEmail + "\n\n" + message);
        
        mailSender.send(email);
    }
}
