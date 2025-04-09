package com.email_service.controller;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import com.email_service.service.EmailService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class EmailController {

  private final List<EmailService> emailServices;

  @Value("${app.email-provider:SMTP}")
  private EmailProvider currentEmailProvider;

  @PostMapping("/contact-us")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public EmailDto.EmailResponse sendEmail(@RequestBody EmailDto.EmailRequest emailRequest) {
    var optionalEmailService =
        emailServices.stream().filter(es -> es.support(currentEmailProvider)).findAny();
    return optionalEmailService
        .map(emailService -> emailService.sendEmail(emailRequest))
        .orElseThrow(() -> new RuntimeException("Email service provider is not available"));
  }
}
