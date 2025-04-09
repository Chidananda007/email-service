package com.email_service.dto;

import lombok.Builder;
import lombok.Data;

public record EmailDto() {

  public record EmailRequest(
      String name, String receiver, String userEmail, String subject, String message) {}

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
