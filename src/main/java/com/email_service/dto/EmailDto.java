package com.email_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

public record EmailDto() {

  public record EmailRequest(
      @NotNull String name,
      @NotNull String receiver,
      @JsonProperty("user_email") String userEmail,
      String message,
      String mobile) {}

  public record BulkEmailRequest(
      @NotNull String subject,
      @NotNull @NotEmpty List<String> receivers,
      String name,
      @JsonProperty("user_email") String userEmail,
      String message,
      String mobile) {}

  public record EmailResponse(
      String receiver, String status, String message, String providerResponse) {}

  public record BulkEmailResponse(List<EmailResponse> results) {}

  @Data
  public static class EmailTo {
    private String email;
    private String name;
  }

  @Data
  public static class EmailFrom {
    private String email;
  }
}
