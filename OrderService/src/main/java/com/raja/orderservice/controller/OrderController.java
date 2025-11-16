package com.raja.orderservice.controller;

import com.raja.orderservice.dto.OrderRequestDTO;
import com.raja.orderservice.dto.OrderResponseDTO;
import com.raja.orderservice.exception.InvalidJSTTokenException;
import com.raja.orderservice.model.Order;
import com.raja.orderservice.model.OrderStatus;
import com.raja.orderservice.model.Product;
import com.raja.orderservice.service.OrderService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    OrderService orderService;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @PostMapping("/{jwtToken}")
    public OrderResponseDTO createOrder(@RequestBody OrderRequestDTO orderRequestDTO,
                                        @PathVariable("jwtToken") String jwtToken) {

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(jwtToken).getPayload();

        Long expiryTime = (Long)claims.get("expirationDate");
        Long now = System.currentTimeMillis();
        log.error(expiryTime + " " + now);
        if(! (expiryTime > now ))
        {
            throw new InvalidJSTTokenException("Invalid Session. Please login again");
        }
        log.error(orderRequestDTO.getProducts().toString());
        List<Product> products = orderRequestDTO.getProducts();
        log.error(products.toString());
        OrderResponseDTO orderResponseDTO = orderService.placeOrder(products, claims.get("email").toString());

        if(orderResponseDTO == null)
        {
            throw new RuntimeException("Order not created. Please try again");
        }

        return orderResponseDTO;
    }

}
