package com.email_service.service;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public abstract class MailgunService implements EmailService {

  @Value("${mailgun.api.key:}")
  private String apiKey;

  @Value("${mailgun.domain:}")
  private String domain;

  @Value("${mailgun.sender:}")
  private String senderEmail;

  @Value("${mailgun.admin.email:}")
  private String adminEmail;

  private final OkHttpClient client = new OkHttpClient();

  @Override
  public boolean support(EmailProvider emailProvider) {
    return EmailProvider.MAILGUN.equals(emailProvider);
  }

  @Override
  public EmailDto.EmailResponse sendEmail(EmailDto.EmailRequest emailRequest) {
    String url = "https://api.mailgun.net/v3/" + domain + "/messages";

    String subject = "New Contact Form Submission";
    String body =
        "You received a new contact form message:\n\n"
            + "Name: "
            + emailRequest.name()
            + "\n"
            + "Email: "
            + emailRequest.receiver()
            + "\n"
            + "Message: "
            + emailRequest.message()
            + "\n";

    var formBody =
        new FormBody.Builder()
            .add("from", "Contact Form <" + senderEmail + ">")
            .add("to", adminEmail)
            .add("subject", subject)
            .add("text", body)
            .build();

    Request request =
        new Request.Builder()
            .url(url)
            .post(formBody)
            .addHeader("Authorization", Credentials.basic("api", apiKey))
            .build();

    try (Response response = client.newCall(request).execute()) {
      return EmailDto.EmailResponse.builder()
          .status(response.isSuccessful() ? "Success" : "Failed")
          .message(response.message())
          .receiver(emailRequest.receiver())
          .build();
    } catch (Exception e) {
      log.error("Failed to send email :{}", e.getMessage(), e);
      return EmailDto.EmailResponse.builder()
          .status("Failed")
          .receiver(emailRequest.receiver())
          .providerResponse(e.getMessage())
          .build();
    }
  }
}
