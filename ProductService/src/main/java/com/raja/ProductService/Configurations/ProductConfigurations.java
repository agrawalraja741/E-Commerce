package com.raja.ProductService.Configurations;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductConfigurations {

    @Bean
    public RestTemplate  createRestTemplateBean() {
        return new RestTemplate();
    }
}
