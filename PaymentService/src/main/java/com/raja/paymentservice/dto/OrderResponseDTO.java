package com.raja.paymentservice.dto;

import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private String orderStatus;
    private Date orderDate;
    private Double amount;
}
