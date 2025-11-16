package com.raja.orderservice.dto;

import com.raja.orderservice.model.OrderStatus;
import lombok.Data;

import java.util.Date;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private OrderStatus orderStatus;
    private Date orderDate;
    private Double amount;
}
