package com.lost.productservicessst.controllers;

import com.lost.productservicessst.models.Product;
import com.lost.productservicessst.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// This controller is capable to host HTTP
//localhost:8080/products -> ProductController
@RestController
@RequestMapping("/products")
public class ProductController { // waiter
    //localhost:8080/products -> the request will reach to controller
    private final ProductService productService;
    ProductController(@Qualifier("selfProductService") ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/{id}") //localhost:8080/products/10
    public Product getProductById(@PathVariable("id") Long id){

        return productService.getProductById(id);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return null;
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        return productService.createProduct(product);
    }
}
