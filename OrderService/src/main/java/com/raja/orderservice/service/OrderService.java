package com.raja.orderservice.service;

import com.raja.orderservice.dto.OrderResponseDTO;
import com.raja.orderservice.model.Order;
import com.raja.orderservice.model.Product;

import java.util.List;

public interface OrderService {

    public OrderResponseDTO placeOrder(List<Product> products, String email);
}
