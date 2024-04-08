package com.lost.productservicessst.services;

import com.lost.productservicessst.DTOs.FakeStoreProductDto;
import com.lost.productservicessst.exceptions.ProductNotFoundException;
import com.lost.productservicessst.models.Category;
import com.lost.productservicessst.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
@Service
public class FakestoreProductService implements ProductService {
    public Product getProductById(Long id) {

        //call the fakestore api to get the product with given id
        RestTemplate restTemplate = new RestTemplate();
        FakeStoreProductDto fakeStoreProductDto =
                restTemplate.getForObject("https://fakestoreapi.com/products/"+id,
                FakeStoreProductDto.class);
        if(fakeStoreProductDto==null) {
            throw new ProductNotFoundException(id, "Please pass a valid product id");
        }
        //convert fakestoreproductdto object to product object

        return convertfakeStoreProductDtotoProduct(fakeStoreProductDto);
    }


    public List<Product> getAllProducts() {
        RestTemplate restTemplate=new RestTemplate();
        List<FakeStoreProductDto> fakeStoreProductDtos =
                restTemplate.getForObject("https://fakestoreapi.com/product",
                List.class);
        List<Product> products = new ArrayList<>();
        assert fakeStoreProductDtos != null;
        for(FakeStoreProductDto fakeStoreProductDto: fakeStoreProductDtos){
            products.add(convertfakeStoreProductDtotoProduct(fakeStoreProductDto));
        }
        return products;

    }
    private Product convertfakeStoreProductDtotoProduct(FakeStoreProductDto fakeStoreProductDto ) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setDescription(fakeStoreProductDto.getCategory());
        product.setCategory(category);

        return product;
    }
}

