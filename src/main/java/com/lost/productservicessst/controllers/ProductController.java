package com.lost.productservicessst.controllers;

import ch.qos.logback.core.testUtil.RandomUtil;
import com.lost.productservicessst.DTOs.FakeStoreProductDto;
import com.lost.productservicessst.models.Product;
import com.lost.productservicessst.services.FakestoreProductService;
import com.lost.productservicessst.services.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// This controller is capable to host HTTP
//localhost:8080/products -> ProductController
@RestController
@RequestMapping("/products")
public class ProductController { // waiter
    //localhost:8080/products -> the request will reach to controller
    private ProductService productService;
    ProductController(FakestoreProductService fakeStoreProductService){
        this.productService = fakeStoreProductService;
    }
    @GetMapping("/{id}") //localhost:8080/products/10
    public Product getProductById(@PathVariable("id") Long id){

        return productService.getProductById(id);
    }
    @GetMapping
    public List<Product> getAllProducts(){
        return null;
    }

}
