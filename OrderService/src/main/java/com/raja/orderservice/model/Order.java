package com.raja.orderservice.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Order extends BaseEntity {

    private Long userId;
    private Long productId;
    private int quantity;
    private double price;
    private Long PaymentId;
}
