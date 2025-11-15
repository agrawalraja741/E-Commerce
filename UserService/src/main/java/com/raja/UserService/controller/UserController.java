package com.raja.UserService.controller;

import com.raja.UserService.DTO.SignUpRequestDTO;
import com.raja.UserService.DTO.SignUpResponseDTO;
import com.raja.UserService.DTO.LoginRequestDTO;
import com.raja.UserService.model.User;
import com.raja.UserService.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Value("${jwt.secret}")
    private String jwtSecret;

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

        return userService.login(loginRequestDTO.getEmail(), loginRequestDTO.getPassword());
    }

    @PostMapping("/validate/{jwtToken}")
    public String ValidateUser(@RequestBody LoginRequestDTO loginRequestDTO, @PathVariable String jwtToken) {

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(jwtToken).getPayload();

        Long expiryTime = (Long)claims.get("expirationDate");
        Long now = System.currentTimeMillis();

        if(! (expiryTime*1000L > now && claims.get("email").toString().equalsIgnoreCase(loginRequestDTO.getEmail())))
        {
            throw new RuntimeException("Invalid Session. Please login again");
        }

        return "Validated";
    }

    }
