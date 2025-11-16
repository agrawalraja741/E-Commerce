package com.raja.orderservice.service;

import com.raja.orderservice.dto.OrderResponseDTO;
import com.raja.orderservice.dto.ProductDTO;
import com.raja.orderservice.model.Order;
import com.raja.orderservice.model.OrderStatus;
import com.raja.orderservice.model.Product;
import com.raja.orderservice.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderResponseDTO placeOrder( List<Product> products , String email) {

        Order order = new Order();
        order.setEmail(email);

        double total = 0.0;
        for(Product p : products){
            ProductDTO productDTO = restTemplate.getForObject("http://localhost:8081/products/"+p.getProductId(), ProductDTO.class);
            total += productDTO.getProductPrice()*p.getQuantity();
        }

        order.setPrice(total);
        order.setProducts(products);
        order.setStatus(OrderStatus.PENDING);

        Order createdOrder = orderRepository.save(order);

        log.error("createdOrder " + createdOrder.toString());


        OrderResponseDTO responseDTO = new OrderResponseDTO();
        responseDTO.setAmount(createdOrder.getPrice());
        responseDTO.setOrderId(createdOrder.getId());
        responseDTO.setOrderDate(new Date());
        responseDTO.setOrderStatus(createdOrder.getStatus());

        String paymentLink = restTemplate.postForObject("http://localhost:7000/payments/initiate", responseDTO, String.class);

        responseDTO.setPaymentLink(paymentLink);

        return responseDTO;
    }
}
