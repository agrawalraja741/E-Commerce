package com.raja.orderservice.dto;

import com.raja.orderservice.model.Product;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private List<Product> products;
}
