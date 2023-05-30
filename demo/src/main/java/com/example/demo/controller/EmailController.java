package com.example.demo.controller;

import com.example.demo.DTO.RequestMailBody;
import com.example.demo.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mailservice")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-email")
    public String sendEmail(@RequestBody RequestMailBody requestMailBody) {
        String recipient= requestMailBody.getRecipient();
        try {
            emailService.sendEmail(requestMailBody.getRecipient(), requestMailBody.getSubject(), requestMailBody.getBody());
            return "Email sent successfully.";
        } catch (MessagingException e) {
            return "Failed to send email.";
        }
    }
}