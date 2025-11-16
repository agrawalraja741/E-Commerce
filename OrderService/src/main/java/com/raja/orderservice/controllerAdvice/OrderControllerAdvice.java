package com.raja.orderservice.controllerAdvice;


import com.raja.orderservice.dto.ErrorResponseDTO;
import com.raja.orderservice.exception.InvalidJSTTokenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class OrderControllerAdvice {

    @ExceptionHandler( InvalidJSTTokenException.class)
    public ResponseEntity<ErrorResponseDTO> handleInvalidJWTTokenException(InvalidJSTTokenException e) {
        ErrorResponseDTO exceptionDto = new ErrorResponseDTO();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus(HttpStatus.FORBIDDEN.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler( RuntimeException.class)
    public ResponseEntity<ErrorResponseDTO> handleOrderControllerException(Exception e) {
        ErrorResponseDTO exceptionDto = new ErrorResponseDTO();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
