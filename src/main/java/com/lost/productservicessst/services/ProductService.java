package com.lost.productservicessst.services;

import com.lost.productservicessst.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);

}
