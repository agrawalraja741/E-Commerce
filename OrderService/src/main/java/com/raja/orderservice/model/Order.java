package com.raja.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "EOrder")
public class Order extends BaseEntity {

    private String email;

    @OneToMany
    private List<Product> products;
    private double price;
    private Long PaymentId;
    private OrderStatus status;
}
