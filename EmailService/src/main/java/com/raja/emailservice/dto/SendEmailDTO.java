package com.raja.emailservice.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class SendEmailDTO {
    private String email;
    private String subject;
    private String body;
}
