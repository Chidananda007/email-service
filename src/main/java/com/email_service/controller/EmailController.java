package com.email_service.controller;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import com.email_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class EmailController {

    private final List<EmailService> emailServices;

    private static  final EmailProvider currentEmailProvider = EmailProvider.MAILGUN;

    @PostMapping("/email")
    public EmailDto.EmailResponse sendEmail(@RequestBody EmailDto.EmailRequest emailRequest) {
        var optionalEmailService =
                emailServices.stream().filter(es -> es.support(currentEmailProvider)).findAny();
       return optionalEmailService.map(emailService->emailService.sendEmail(emailRequest))
               .orElseThrow(()-> new RuntimeException("email service is not available"));
    }
}
