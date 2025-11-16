package com.raja.orderservice.dto;

import com.raja.orderservice.model.OrderStatus;
import com.raja.orderservice.model.Product;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderResponseDTO {

    private Long orderId;
    private OrderStatus orderStatus;
    private Date orderDate;
    private Double amount;
    private String paymentLink;
    private List<Product> products;
    private Long PaymentId;
}
