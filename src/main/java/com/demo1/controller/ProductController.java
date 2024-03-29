package com.demo1.controller;

import com.demo1.history.Product;
import com.demo1.service.ProductService;
import com.demo1.service.TermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<Void> createProduct(@RequestBody(required = true) Product product) {
        productService.register(product);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
