package com.raja.ProductService.Controller;

import com.raja.ProductService.DTO.ProductDTO;
import com.raja.ProductService.Model.Product;
import com.raja.ProductService.Service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockitoBean
    private ProductService productService;

    @Test
    void getSingleProduct() throws Exception{
        Long productId = 1L;

        Product expectedProduct = new Product();
        expectedProduct.setId(productId);
        String productName = "Product Name";
        expectedProduct.setProductName(productName);
        String description = "Product Description";
        expectedProduct.setProductDescription(description);
        when(productService.getSingleProduct(productId)).thenReturn(expectedProduct);

        assertEquals(expectedProduct.getId(), productController.getSingleProduct(productId).getId() );
        assertEquals(productName, productController.getSingleProduct(productId).getProductName() );
        assertEquals(description, productController.getSingleProduct(productId).getProductDescription() );

    }

    @Test
    void getAllProducts() throws Exception{

        ArrayList<Product> expectedProducts = new ArrayList<Product>();

        Product expectedProduct1 = new Product();
        Product expectedProduct2 = new Product();

        String productName1 = "Product Name1";
        expectedProduct1.setProductName(productName1);
        String description1 = "Product Description1";
        expectedProduct1.setProductDescription(description1);

        String productName2 = "Product Name2";
        expectedProduct2.setProductName(productName2);
        String description2 = "Product Description2";
        expectedProduct2.setProductDescription(description2);

        expectedProducts.add(expectedProduct1);
        expectedProducts.add(expectedProduct2);
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        assertEquals(productName1, productController.getAllProducts().get(0).getProductName() );
        assertEquals(description1, productController.getAllProducts().get(0).getProductDescription() );
        assertEquals(productName2, productController.getAllProducts().get(1).getProductName() );
        assertEquals(description2, productController.getAllProducts().get(1).getProductDescription() );

    }

    @Test
    void createProduct() throws Exception{

        Product expectedProduct = new Product();
        String productName = "Product Name";
        expectedProduct.setProductName(productName);
        String description = "Product Description";
        expectedProduct.setProductDescription(description);
        when(productService.createProduct(new Product())).thenReturn(expectedProduct);

        assertEquals(productName, productController.createProduct(new Product()).getProductName() );
        assertEquals(description, productController.createProduct(new Product()).getProductDescription() );

    }
}