package com.lost.productservicessst.exceptions;

public class ProductNotFoundException extends RuntimeException{
    public Long id;
    public ProductNotFoundException(Long id, String message){
        super(message);
        this.id = id;
    }
}
