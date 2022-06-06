package com.example.demo.database;

import com.example.demo.entity.ProductEntity;
import com.example.demo.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Database {
    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                ProductEntity product1 = new ProductEntity("k");
                ProductEntity product2 = new ProductEntity("n");
                System.out.println("insert db " + productRepository.save(product1));
                System.out.println("insert db " + productRepository.save(product2));
            }
        };
    }
}
