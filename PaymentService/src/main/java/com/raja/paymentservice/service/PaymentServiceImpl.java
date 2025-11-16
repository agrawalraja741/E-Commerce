package com.raja.paymentservice.service;

import com.raja.paymentservice.dto.OrderResponseDTO;
import com.raja.paymentservice.paymentGateway.PaymentGateway;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentGateway paymentGateway;

    public PaymentServiceImpl(PaymentGateway paymentGateway) {
        this.paymentGateway = paymentGateway;
    }

    @Override
    public String generatePaymentLink(OrderResponseDTO orderResponseDTO) throws StripeException {
        return paymentGateway.generatePaymentLink(orderResponseDTO);
    }
}
