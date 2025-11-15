package com.raja.UserService.DTO;

import com.raja.UserService.model.Role;
import lombok.Data;

import java.util.List;

@Data
public class SignUpRequestDTO {

    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;
    private List<Role> roles;
}
