package com.raja.orderservice.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String message;
    private String status;
}
