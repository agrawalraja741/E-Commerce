package com.raja.ProductService.Controller;

import com.raja.ProductService.DTO.ProductDTO;
import com.raja.ProductService.Exceptions.CategoryNotFoundException;
import com.raja.ProductService.Exceptions.ProductNotFoundException;
import com.raja.ProductService.Model.Product;
import com.raja.ProductService.Service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

    private final RestTemplate restTemplate;
    private final ProductService productService;

    public ProductController(ProductService productService,
                             RestTemplate restTemplate) {
        this.productService = productService;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ProductDTO getSingleProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {

        Product product =  productService.getSingleProduct(productId);
        return ProductDTO.fromProduct(product);
    }


    @GetMapping("/")
    public List<ProductDTO> getAllProducts() {
         List<Product> products  = productService.getAllProducts();
         List<ProductDTO> productDTOs = new ArrayList<>();
         for(Product product : products){
            productDTOs.add(ProductDTO.fromProduct(product));
         }

         return productDTOs;
    }





    @PostMapping("/")
    public ProductDTO createProduct(@RequestBody Product product) throws CategoryNotFoundException   {
        return ProductDTO.fromProduct(productService.createProduct(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        productService.deleteProductById(productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*

    @GetMapping("/title/{title}/{pageNumber}/{pageSize}")
    public Page<Product> getProductByTitle(@PathVariable("title") String title, @PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        return productService.getProductsByTitle(title, pageNumber, pageSize);
    }



    @GetMapping("/searchByTitle/{title}")
    List<Product> getProductsByTitle(@PathVariable String title) {
        //select * from products where lower(title) LIKE '%iphone%'

        return null;
    }


     */

}
