package com.raja.UserService.service;

import com.raja.UserService.exception.UserAlreadyExist;
import com.raja.UserService.model.User;
import com.raja.UserService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    public User signUp(User user)
    {
        Optional<User> userOptional = userRepository.findByEmail(user.getEmail());

        if(userOptional.isPresent())
        {
            throw new UserAlreadyExist("User already exist with the email address");
        }
        return userRepository.save(user);
    }
}
