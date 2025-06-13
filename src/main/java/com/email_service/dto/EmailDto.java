package com.email_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

public record EmailDto() {

  public record EmailRequest(
      String name,
      String receiver,
      @JsonProperty("user_email") String userEmail,
      String message,
      String mobile) {}

  @Builder
  public record EmailResponse(
      String receiver, String status, String message, String providerResponse) {}

  @Data
  @Builder
  public static class EmailTo {
    private String email;
    private String name;
  }

  @Builder
  @Data
  public static class EmailFrom {
    private String email;
  }
}
