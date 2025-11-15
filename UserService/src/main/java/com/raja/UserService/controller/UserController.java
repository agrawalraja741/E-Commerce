package com.raja.UserService.controller;

import com.raja.UserService.DTO.SignUpRequestDTO;
import com.raja.UserService.DTO.SignUpResponseDTO;
import com.raja.UserService.DTO.LoginRequestDTO;
import com.raja.UserService.model.User;
import com.raja.UserService.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    private UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public SignUpResponseDTO registerUser(@RequestBody SignUpRequestDTO signUpRequestDTO) {

        SignUpResponseDTO responseDTO = new SignUpResponseDTO();
        if(signUpRequestDTO == null)
        {
            responseDTO.setStatus(HttpStatus.BAD_REQUEST);
            responseDTO.setResponse("Please provide a valid details to sign up");
            return responseDTO;
        }
        User newUser = new User();
        newUser.setName(signUpRequestDTO.getName());
        newUser.setEmail(signUpRequestDTO.getEmail());
        newUser.setPassword(signUpRequestDTO.getPassword());
        newUser.setPhone(signUpRequestDTO.getPhone());
        newUser.setAddress(signUpRequestDTO.getAddress());
        newUser.setRoles(signUpRequestDTO.getRoles());

        User createdUser =  userService.signUp(newUser);

        responseDTO.setEmail(createdUser.getEmail());
        responseDTO.setStatus(HttpStatus.CREATED);
        responseDTO.setResponse("User registered successfully");
        return responseDTO;
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody LoginRequestDTO loginRequestDTO) {
        return null;
    }

    }
