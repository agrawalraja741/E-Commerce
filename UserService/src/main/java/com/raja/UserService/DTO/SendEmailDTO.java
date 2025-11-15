package com.raja.UserService.DTO;

import lombok.Data;

@Data
public class SendEmailDTO {
    private String email;
    private String subject;
    private String body;
}
