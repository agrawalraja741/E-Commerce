package com.raja.UserService.service;

import com.raja.UserService.model.User;

public interface UserService {

    public User signUp(User user);
    public String login(String email, String password);
    //public boolean delete(String email);
}
