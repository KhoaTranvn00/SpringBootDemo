package com.example.demo.database;

import com.example.demo.models.Product;
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
                Product product1 = new Product("k");
                Product product2 = new Product("n");
                System.out.println("insert db " + productRepository.save(product1));
                System.out.println("insert db " + productRepository.save(product2));
            }
        };
    }
}
