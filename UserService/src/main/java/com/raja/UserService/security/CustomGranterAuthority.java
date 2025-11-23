package com.raja.UserService.security;

import com.raja.UserService.model.Role;
import org.springframework.security.core.GrantedAuthority;

public class CustomGranterAuthority implements GrantedAuthority {
    private Role role;

    public CustomGranterAuthority(Role role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role.getValue();
    }
}