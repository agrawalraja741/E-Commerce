package com.raja.paymentservice.controllerAdvice;

import com.raja.paymentservice.dto.ErrorResponseDTO;
import com.raja.paymentservice.exception.PaymentNotCratedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentControllerAdvice {

    @ExceptionHandler(PaymentNotCratedException.class)
    public ResponseEntity<ErrorResponseDTO> handlePaymentExceptions(Exception exception) {
        ErrorResponseDTO exceptionDto = new ErrorResponseDTO();
        exceptionDto.setMessage(exception.getMessage());
        exceptionDto.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
