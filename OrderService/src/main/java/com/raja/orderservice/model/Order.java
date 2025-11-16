package com.raja.orderservice.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name = "EOrder")
public class Order extends BaseEntity {

    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Product> products;
    private double price;
    private Long PaymentId;
    private OrderStatus status;
}
