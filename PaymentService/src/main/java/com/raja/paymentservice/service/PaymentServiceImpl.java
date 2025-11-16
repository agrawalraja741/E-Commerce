package com.raja.paymentservice.service;

import com.raja.paymentservice.dto.OrderResponseDTO;
import com.raja.paymentservice.exception.PaymentNotCratedException;
import com.raja.paymentservice.model.Payment;
import com.raja.paymentservice.model.PaymentStatus;
import com.raja.paymentservice.paymentGateway.PaymentGateway;
import com.raja.paymentservice.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentGateway paymentGateway;
    private PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentGateway paymentGateway , PaymentRepository paymentRepository) {
        this.paymentGateway = paymentGateway;
        this.paymentRepository = paymentRepository;
    }

    @Override
    public String generatePaymentLink(OrderResponseDTO orderResponseDTO) throws StripeException {

        Payment payment = new Payment();
        payment.setPaymentStatus(PaymentStatus.PENDING);
        payment.setOrderId(orderResponseDTO.getOrderId());
        payment.setAmount(orderResponseDTO.getAmount());

        Payment createdPayment = paymentRepository.save(payment);

        if(createdPayment.getId() <=0)
        {
            throw new PaymentNotCratedException("Payment not crated. Please try again");
        }
        return paymentGateway.generatePaymentLink(orderResponseDTO);
    }
}
