package com.raja.ProductService.DTO;

import com.raja.ProductService.Model.Product;
import lombok.Data;

@Data
public class ProductDTO {

    private long id;

    private String productName;

    private String productDescription;

    private double productPrice;

    public static ProductDTO fromProduct(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.id = product.getId();
        productDTO.productName = product.getProductName();
        productDTO.productDescription = product.getProductDescription();
        productDTO.productPrice = product.getProductPrice();
        return productDTO;
    }
}
