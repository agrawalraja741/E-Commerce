package com.raja.ProductService.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Product extends BaseEntity {

    private String productName;

    private String productDescription;

    private double productPrice;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn
    private Category category;
}
