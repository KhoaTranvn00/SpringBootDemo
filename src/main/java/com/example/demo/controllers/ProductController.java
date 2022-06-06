package com.example.demo.controllers;

import com.example.demo.entity.ProductEntity;
import com.example.demo.entity.ResponseObject;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    ProductRepository repository;
    @Autowired
    ProductService productService;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllProduct() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "get all product success", productService.getAllProduct())
        );
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getDetailProduct(@PathVariable Long id) {
//        Optional<ProductEntity> foundProduct = repository.findById(id);
//        Optional<ProductEntity> foundProduct = productService.getDetailProduct(id);


        Optional<Product> foundProduct = Optional.ofNullable(productService.getProductById(id));    // useable
        if(foundProduct.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "get detail product success1", foundProduct)
            );
        } else return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("fail", "not found id product", null)
        );
    }

    @PostMapping("")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody ProductEntity newProduct) {
//        List<ProductEntity> foundProducts = repository.findByName(newProduct.getName().trim());
        List<Product> foundProducts = productService.getFindByName(newProduct.getName().trim());
        if(foundProducts.size() == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "insert product success1", repository.save(newProduct))
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("fail", "product name already exist1", null)
            );
    }
}

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody ProductEntity newProduct, @PathVariable Long id) {
        Optional<ProductEntity> updateProduct = repository.findById(id);
        if(updateProduct.isPresent()) {
            ProductEntity p = updateProduct.get();
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
//        Optional<ProductEntity> foundProduct = repository.findById(id);
        if(productService.deleteProductById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "delete product success", "")
            );
        } else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("fail", "not found id product to delete", null)
            );
    }
}
