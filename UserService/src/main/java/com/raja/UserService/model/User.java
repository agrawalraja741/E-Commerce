package com.raja.UserService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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

    @OneToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
