package com.raja.orderservice.service;

import com.raja.orderservice.model.Order;
import com.raja.orderservice.model.Product;

import java.util.List;

public interface OrderService {

    public Order placeOrder( List<Product> products, String email);
}
