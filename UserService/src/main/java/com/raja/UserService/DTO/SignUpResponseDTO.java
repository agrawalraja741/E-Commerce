package com.raja.UserService.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Data
public class SignUpResponseDTO {

    private String email;
    private HttpStatus status;
    private String response;

}
