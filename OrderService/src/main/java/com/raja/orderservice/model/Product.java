package com.raja.orderservice.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Product extends BaseEntity {
    private Long productId;
    private int quantity;
}
