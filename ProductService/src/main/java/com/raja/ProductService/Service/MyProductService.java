package com.raja.ProductService.Service;

import com.raja.ProductService.Exceptions.ProductNotFoundException;
import com.raja.ProductService.Model.Product;
import com.raja.ProductService.Repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MyProductService implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    public Product getSingleProduct(Long productId) throws ProductNotFoundException
    {
        Optional<Product> product = productRepository.findById(productId);

        if(product.isEmpty())
        {
            throw new ProductNotFoundException("Product with product id is not present");
        }

        log.error("product fetched " + product.get());
        return product.get();
    }

    public List<Product> getAllProducts()
    {
        List<Product> products = productRepository.findAll();
        return products;
    }

}
