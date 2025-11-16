package com.raja.paymentservice.service;

import com.raja.paymentservice.dto.OrderResponseDTO;
import com.stripe.exception.StripeException;
import org.hibernate.query.Order;

public interface PaymentService {
    String generatePaymentLink(OrderResponseDTO orderResponseDTO) throws StripeException;
}
