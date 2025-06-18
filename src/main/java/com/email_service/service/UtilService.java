package com.email_service.service;

import com.email_service.dto.EmailProvider;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilService {

  private final List<EmailService> emailServices;

  @Value("${app.email-provider:SMTP}")
  private EmailProvider currentAvailableProvider;

  public EmailService validateProvider() {
    return emailServices.stream()
        .filter(es -> es.support(currentAvailableProvider))
        .findAny()
        .orElseThrow(
            () ->
                new RuntimeException(
                    "Email service provider "
                        + currentAvailableProvider
                        + " is not supported by the application"));
  }
}
