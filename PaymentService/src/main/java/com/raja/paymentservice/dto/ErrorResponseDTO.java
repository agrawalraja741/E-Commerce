package com.raja.paymentservice.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String message;
    private String status;
}
