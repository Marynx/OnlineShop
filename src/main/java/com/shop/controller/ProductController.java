package com.shop.controller;


import com.shop.repository.ItemRepository;
import com.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;



public class ProductController {
    private ProductRepository productRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ProductController(ProductRepository productRepository,ItemRepository itemRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
    }




}
