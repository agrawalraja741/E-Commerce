package com.raja.UserService.controllerAdvice;

import com.raja.UserService.DTO.ErrorResponseDTO;
import com.raja.UserService.exception.PasswordMismatchException;
import com.raja.UserService.exception.UserAlreadyExist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler({PasswordMismatchException.class, UserAlreadyExist.class, RuntimeException.class})
    public ResponseEntity<ErrorResponseDTO> handleUserControllerException(Exception e) {
        ErrorResponseDTO exceptionDto = new ErrorResponseDTO();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus(HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }
}
