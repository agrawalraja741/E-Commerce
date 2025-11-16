package com.raja.paymentservice.controller;

import com.raja.paymentservice.dto.OrderResponseDTO;
import com.raja.paymentservice.service.PaymentService;
import com.stripe.exception.StripeException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public String initiatePayment(OrderResponseDTO orderResponseDTO) throws StripeException {
        return paymentService.generatePaymentLink(orderResponseDTO);
    }
}
