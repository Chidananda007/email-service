package com.email_service.service;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSenderService implements EmailService {

  private final JavaMailSender emailSender;

  private static final String RECEIVER_EMAIL = "chidanandabazar007@gmail.com";

  @Override
  public boolean support(EmailProvider emailProvider) {
    return EmailProvider.SMTP.equals(emailProvider);
  }

  @Override
  public EmailDto.EmailResponse sendEmail(EmailDto.EmailRequest emailRequest) {
    try {
      SimpleMailMessage message = getSimpleMailMessage(emailRequest);
      emailSender.send(message);
      log.info(
          "Email sent successfully to: {}",
          Objects.nonNull(message.getTo()) ? message.getTo() : null);
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

  @NotNull
  private static SimpleMailMessage getSimpleMailMessage(EmailDto.EmailRequest emailRequest) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("A new contact request");
    message.setText(
        "Name: "
            + emailRequest.name()
            + "\n"
            + "Email: "
            + emailRequest.userEmail()
            + "\n"
            + "Mobile: "
            + emailRequest.mobile()
            + "\n"
            + "Message: "
            + emailRequest.message());
    message.setTo(
        Objects.nonNull(emailRequest.receiver()) ? emailRequest.receiver() : RECEIVER_EMAIL);
    message.setCc("chidananda676@gmail.com");
    return message;
  }
}
