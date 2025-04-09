package com.email_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Builder;
import lombok.Data;

public class Msg91Dto {

  @Data
  public static class EmailRequest {
    private String domain;

    @JsonProperty("template_id")
    private String templateId;

    @JsonProperty private EmailDto.EmailFrom from;

    @JsonProperty("recipients")
    private List<EmailRecipient> recipients;
  }

  @Data
  @Builder
  public static class EmailRecipient {
    private List<EmailTo> to;
  }

  @Data
  @Builder
  public static class EmailTo {
    private String email;
    private String name;
  }

  @Data
  public static class EmailResponse {
    private String status;
    private EmailData data;
    private EmailRequest errors;
    private boolean hasError;
    private String message;
  }

  @Data
  public static class EmailData {
    @JsonProperty("unique_id")
    private String uniqueId;
  }
}
