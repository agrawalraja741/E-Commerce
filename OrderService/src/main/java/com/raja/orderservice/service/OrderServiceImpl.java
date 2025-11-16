package com.raja.orderservice.service;

import com.raja.orderservice.dto.ProductDTO;
import com.raja.orderservice.model.Order;
import com.raja.orderservice.model.OrderStatus;
import com.raja.orderservice.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Order placeOrder( List<Product> products , String email) {

        //call product MS to get product detials
        //calculate final amound
        //create order with pending status

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

        log.error("Order " + order.toString());

        // go for payment MS
        return null;
    }
}
