package com.app.controller;

import com.app.entity.Product;
import com.app.service.productservice.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/buyer/products")
public class ProductController {
    @Autowired
    IProductService productService;
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        productService.save(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
