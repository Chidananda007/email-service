package com.email_service.service;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSenderService implements EmailService {

  private final JavaMailSender emailSender;

  private final String RECEIVER_EMAIL = "chidananda@gmail.com";

  @Value("${app.msg91.authkey}")
  private String msgAuthkey;

  @Value("${app.msg91.emailUrl}")
  private String emailUrl;

  @Override
  public boolean support(EmailProvider emailProvider) {
    return EmailProvider.SMTP.equals(emailProvider);
  }

  @Override
  public EmailDto.EmailResponse sendEmail(EmailDto.EmailRequest emailRequest) {
    try {
      SimpleMailMessage message = new SimpleMailMessage();
      message.setSubject("New Contact Request from " + emailRequest.userEmail());
      message.setText("Message from " + emailRequest.userEmail() + ":\n\n" + message);
      message.setTo(RECEIVER_EMAIL);
      emailSender.send(message);
      return EmailDto.EmailResponse.builder()
          .status("success")
          .receiver(emailRequest.receiver())
          .build();
    } catch (Exception e) {
      log.error("Failed to send email :{}", e.getMessage(), e);
      return EmailDto.EmailResponse.builder()
          .status("Failed")
          .receiver(emailRequest.receiver())
          .message(e.getMessage())
          .build();
    }
  }
}
