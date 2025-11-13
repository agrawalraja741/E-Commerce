package com.raja.ProductService.controllerAdvice;

import com.raja.ProductService.DTO.ErrorResponseDTO;
import com.raja.ProductService.Exceptions.CategoryNotFoundException;
import com.raja.ProductService.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleProductNotFoundException(ProductNotFoundException e) {
        ErrorResponseDTO exceptionDto = new ErrorResponseDTO();
        //TODO
        // exceptionDto.setProductId(????);

        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleCategoryNotFoundException(CategoryNotFoundException e) {
        ErrorResponseDTO exceptionDto = new ErrorResponseDTO();
        //TODO
        // exceptionDto.setProductId(????);

        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus(HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
