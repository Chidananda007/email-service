package com.email_service.service;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

  private String subject;
  private String textMessage;

  private static final String RECEIVER_EMAIL = "chidanandabazar007@gmail.com";

  @Override
  public boolean support(EmailProvider emailProvider) {
    return EmailProvider.SMTP.equals(emailProvider);
  }

  @Override
  public EmailDto.EmailResponse sendEmail(EmailDto.EmailRequest emailRequest) {
    try {
      SimpleMailMessage message = getSimpleMailMessage(emailRequest);
      this.subject = "New Contact Request form %s".formatted(emailRequest.name());
      this.textMessage =
          String.format(
              "Name: %s\nEmail: %s\nMessage: %s\nMobile: %s",
              emailRequest.name(),
              emailRequest.userEmail(),
              emailRequest.message(),
              emailRequest.mobile());
      emailSender.send(message);
      log.info(
          "Email sent successfully to: {}",
          Objects.nonNull(message.getTo()) ? message.getTo() : null);
      return new EmailDto.EmailResponse(emailRequest.receiver(), "success", null, null);
    } catch (Exception e) {
      log.error("Failed to send email :{}", e.getMessage(), e);
      return new EmailDto.EmailResponse(emailRequest.receiver(), "Failed", e.getMessage(), null);
    }
  }

  @Override
  public EmailDto.BulkEmailResponse sendBulkEmail(EmailDto.BulkEmailRequest bulkEmailRequest) {
    List<EmailDto.EmailResponse> results = new ArrayList<>();
    this.subject = bulkEmailRequest.subject();
    this.textMessage =
        String.format(
            "Dear %s,\n\n\t%s\n\nRegards,\n%s",
            bulkEmailRequest.name(), bulkEmailRequest.message(), bulkEmailRequest.userEmail());
    for (String receiver : bulkEmailRequest.receivers()) {
      EmailDto.EmailRequest singleRequest =
          new EmailDto.EmailRequest(
              bulkEmailRequest.name(),
              receiver,
              bulkEmailRequest.userEmail(),
              bulkEmailRequest.message(),
              bulkEmailRequest.mobile());
      results.add(sendEmail(singleRequest));
    }
    return new EmailDto.BulkEmailResponse(results);
  }

  @NotNull
  private SimpleMailMessage getSimpleMailMessage(EmailDto.EmailRequest emailRequest) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject(this.subject);
    message.setText(this.textMessage);
    message.setSentDate(new Date());
    message.setTo(emailRequest.receiver());
    message.setCc("chidananda676@gmail.com");
    return message;
  }
}
