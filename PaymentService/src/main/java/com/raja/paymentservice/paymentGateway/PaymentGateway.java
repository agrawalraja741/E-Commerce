package com.raja.paymentservice.paymentGateway;

import com.raja.paymentservice.dto.OrderResponseDTO;
import com.stripe.exception.StripeException;

public interface PaymentGateway {
    String generatePaymentLink(OrderResponseDTO orderResponseDTO) throws StripeException;
}
