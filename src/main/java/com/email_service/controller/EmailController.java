package com.email_service.controller;

import com.email_service.dto.EmailDto;
import com.email_service.service.UtilService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class EmailController {

  private UtilService utilService;

  @PostMapping("/contact-us")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public EmailDto.EmailResponse sendEmail(@Valid @RequestBody EmailDto.EmailRequest emailRequest) {
    return utilService.validateProvider().sendEmail(emailRequest);
  }

  @PostMapping("/bulk-email")
  @ResponseStatus(HttpStatus.ACCEPTED)
  public EmailDto.BulkEmailResponse sendBulkEmail(
      @Valid @RequestBody EmailDto.BulkEmailRequest bulkEmailRequest) {
    return utilService.validateProvider().sendBulkEmail(bulkEmailRequest);
  }
}
