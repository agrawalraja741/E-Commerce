package com.raja.UserService.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raja.UserService.DTO.SendEmailDTO;
import com.raja.UserService.exception.UserAlreadyExist;
import com.raja.UserService.model.User;
import com.raja.UserService.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${jwt.secret}")
    private String jwtSecret;

    public User signUp(User user)
    {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if(userOptional.isPresent())
        {
            throw new UserAlreadyExist("User already exist with the email address");
        }

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        SendEmailDTO sendEmailDTO = new SendEmailDTO();
        sendEmailDTO.setEmail(user.getEmail());
        sendEmailDTO.setSubject("Sign Up Successful");
        sendEmailDTO.setBody("Welcome to Our E-Commerce platform");

        try
        {
            kafkaTemplate.send("sendWelcomeEmail" , objectMapper.writeValueAsString(sendEmailDTO));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return userRepository.save(user);
    }

    @Override
    public String login(String email, String password) {
        if(email.isEmpty() || password.isEmpty())
        {
            throw new RuntimeException("Invalid email or password");
        }
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty())
        {
            throw new RuntimeException("Invalid email");
        }

        boolean matches = bCryptPasswordEncoder.matches(password, userOptional.get().getPassword());
        if(matches== false)
        {
            throw new RuntimeException("Invalid password");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 30);
        Date expirationDate = calendar.getTime();

        Map<String, Object> claims = new HashMap<>();
        claims.put("email", email);
        claims.put("role", "USER");
        claims.put("expirationDate", expirationDate);

        SecretKey secretKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        String jwtToken = Jwts.builder().claims(claims).signWith(secretKey).compact();
        return jwtToken;
    }
}
