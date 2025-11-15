package com.raja.UserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity(name="euser")
public class User extends BaseEntity{

    private String name;
    private String password;
    private String email;
    private String phone;
    private String address;

    @OneToMany
    private List<Role> roles;
}
