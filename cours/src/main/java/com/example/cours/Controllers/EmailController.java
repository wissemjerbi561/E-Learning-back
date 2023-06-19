package com.example.cours.Controllers;

import com.example.cours.Entities.EmailRequest;
import com.example.cours.Services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cours")
public class EmailController {
    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody EmailRequest emailRequest) throws MessagingException {
        String to = emailRequest.getTo();
        String subject = emailRequest.getSubject();
        String body = emailRequest.getBody();

        emailService.sendEmail(to, subject, body);

        return "Email sent successfully!";
    }
}
