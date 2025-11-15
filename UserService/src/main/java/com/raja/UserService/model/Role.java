package com.raja.UserService.model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity(name ="roles")
public class Role extends BaseEntity {
    private String value;
}
