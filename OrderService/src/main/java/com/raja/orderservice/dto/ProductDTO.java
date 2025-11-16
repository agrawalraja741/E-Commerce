package com.raja.orderservice.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private long id;

    private String productName;

    private String productDescription;

    private double productPrice;

}
