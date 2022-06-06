package com.example.demo.service;

import com.example.demo.entity.ProductEntity;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProduct() {
        List<ProductEntity> productsEntity = productRepository.findAll();
        List<Product> products = new ArrayList<>();

        for(ProductEntity product: productsEntity) {
            products.add(new Product(product));
        }

        return products;
    }

    public Product getProductById(Long id) {
        Optional<ProductEntity> product = productRepository.findById(id);

        if(product.isPresent()) return new Product(product);
        else return null;
    }

    public List<Product> getFindByName(String name){
        List<ProductEntity> productEntitieList = productRepository.findByName(name);

        List<Product> productModelList = new ArrayList<>();
        for(ProductEntity productEntity: productEntitieList) {
            productModelList.add(new Product(productEntity));
        }

        return productModelList;
    }

    public boolean deleteProductById(Long id) {
        if(productRepository.existsById(id)) {
            productRepository.deleteById(id);
            return true;
        } else return false;
    }



}
