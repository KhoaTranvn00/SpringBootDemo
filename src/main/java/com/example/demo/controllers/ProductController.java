package com.example.demo.controllers;

import com.example.demo.models.Product;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    ProductRepository repository;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "get all product success", repository.findAll())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getDetailProduct(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "get detail product success", foundProduct)
            );
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("fail", "not found id product", null)
        );
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        List<Product> foundProducts = repository.findByName(newProduct.getName().trim());
        if(foundProducts.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "insert product success", repository.save(newProduct))
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "product name already exist", null)
            );
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Optional<Product> updateProduct = repository.findById(id);
        if(updateProduct.isPresent()) {
            Product p = updateProduct.get();
            p.setName(newProduct.getName());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "update success", repository.save(p))
            );
        } else
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "not found product to update", null)
            );
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        Optional<Product> foundProduct = repository.findById(id);
        if(foundProduct.isPresent()) {
            Product p = foundProduct.get();
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "delete product success", "")
            );
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "not found id product to delete", null)
            );
    }
}
