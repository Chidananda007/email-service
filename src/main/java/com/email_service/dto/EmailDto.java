package com.email_service.dto;

import lombok.Builder;

public record EmailDto() {

    public record EmailRequest(String name , String receiver,
                               String subject,
                               String text) {}

    @Builder
    public record EmailResponse(String receiver,
                                String status,
                                String errorMessage,
                                String providerResponse) {}
}
