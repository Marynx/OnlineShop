package com.shop.controller;


import com.shop.model.Vote;
import com.shop.repository.ItemRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.sql.Date;


@Controller
public class ProductController {
    private ProductRepository productRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ProductController(ProductRepository productRepository,ItemRepository itemRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
    }



}
