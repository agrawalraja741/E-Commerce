package com.raja.ProductService.Service;

import com.raja.ProductService.DTO.ProductDTO;
import com.raja.ProductService.Exceptions.CategoryNotFoundException;
import com.raja.ProductService.Exceptions.ProductNotFoundException;
import com.raja.ProductService.Model.Category;
import com.raja.ProductService.Model.Product;
import com.raja.ProductService.Repository.CategoryRepository;
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

    @Autowired
    private CategoryRepository categoryRepository;

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

    public Product createProduct(Product product) throws CategoryNotFoundException
    {
        log.error(
                product.toString() + product.getCategory().toString()
        );

        if(product.getCategory() == null)
            throw new CategoryNotFoundException("Category id is not present");

        long categoryId = product.getCategory().getId();

        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);

        if(optionalCategory.isEmpty())
        {
            optionalCategory = categoryRepository.findByCategoryName(product.getCategory().getCategoryName());
        }

        if(optionalCategory.isEmpty())
        {
            Category category = new Category();
            category.setCategoryName(product.getCategory().getCategoryName());
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
        else
        {
            product.setCategory(optionalCategory.get());
        }

        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long productId) throws ProductNotFoundException
    {
        Optional<Product> product = productRepository.findById(productId);
        if(product.isEmpty())
            throw new ProductNotFoundException("Product with product id is not present");
        productRepository.deleteById(productId);
    }
}
