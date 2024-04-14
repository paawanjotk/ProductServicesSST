package com.lost.productservicessst.services;

import com.lost.productservicessst.exceptions.CategoryNotFoundException;
import com.lost.productservicessst.exceptions.ProductNotFoundException;
import com.lost.productservicessst.models.Category;
import com.lost.productservicessst.models.Product;
import com.lost.productservicessst.repositories.CategoryRepository;
import com.lost.productservicessst.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService{
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getProductById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException(id, "Product not found");
        }
        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    public Product createProduct(Product product){
        Category category = product.getCategory();
        if(category.getId()==null) {
            product.setCategory(categoryRepository.save(category));
        }
        Product product1 = productRepository.save(product);
        Optional<Category> optionalCategory = categoryRepository.findById(category.getId());

        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException("Category not found");
        }

        product1.setCategory(optionalCategory.get());
        return product1;
    }
}
