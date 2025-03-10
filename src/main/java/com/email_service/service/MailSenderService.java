package com.email_service.service;

import com.email_service.dto.EmailDto;
import com.email_service.dto.EmailProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class MailSenderService implements EmailService {

    private final JavaMailSender emailSender;

    @Override
    public boolean support(EmailProvider emailProvider) {
        return EmailProvider.SMTP.equals(emailProvider);
    }

    @Override
    public EmailDto.EmailResponse sendEmail(EmailDto.EmailRequest emailRequest) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(emailRequest.receiver());
            message.setSubject(emailRequest.subject());
            message.setText(emailRequest.text());
            emailSender.send(message);
           return EmailDto.EmailResponse.builder()
                    .status("success")
                    .receiver(emailRequest.receiver())
                    .build();
        }catch (Exception e){
            log.error("Failed to send email :{}",e.getMessage(), e);
           return EmailDto.EmailResponse.builder()
                    .status("Failed")
                    .receiver(emailRequest.receiver())
                    .errorMessage(e.getMessage())
                    .build();
        }
    }
}
