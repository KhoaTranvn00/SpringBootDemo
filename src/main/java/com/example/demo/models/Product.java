package com.example.demo.models;

import com.example.demo.entity.ProductEntity;

import java.util.Optional;

public class Product {
    private Long id;
    private String name;

    public Product(String name) {
        this.name = name;
    }

    public Product(Optional<ProductEntity> entity) {
        if(entity.isPresent()) {
            this.id = entity.get().getId();
            this.name = entity.get().getName();
        }
    }

    public Product(ProductEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
