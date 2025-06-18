package com.email_service.service;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;

public interface EmailService {

  boolean support(EmailProvider emailProvider);

  EmailDto.EmailResponse contactUsByEmail(EmailDto.EmailRequest emailRequest);

  EmailDto.BulkEmailResponse sendBulkEmail(EmailDto.BulkEmailRequest bulkEmailRequest);
}
