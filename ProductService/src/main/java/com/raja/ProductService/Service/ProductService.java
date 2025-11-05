package com.raja.ProductService.Service;

import com.raja.ProductService.DTO.ProductDTO;
import com.raja.ProductService.Exceptions.CategoryNotFoundException;
import com.raja.ProductService.Exceptions.ProductNotFoundException;
import com.raja.ProductService.Model.Product;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    List<Product> getAllProducts() ;
    Product createProduct(Product product) throws CategoryNotFoundException;
    void deleteProductById(Long productId) throws ProductNotFoundException;
}
