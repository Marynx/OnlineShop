package com.shop.controller;


import com.shop.model.Producer;
import com.shop.model.Product;
import com.shop.model.Vote;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.Date;


@Controller
@RequestMapping("product")
public class ProductController {
    private ProductRepository productRepository;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductController(ProductRepository productRepository,ItemRepository itemRepository,CategoryRepository categoryRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
        this.categoryRepository=categoryRepository;
    }

    @PostMapping("/add")
    public String addProduct(@ModelAttribute @Valid Product product){
        productRepository.save(product);
        return "redirect:/worker";
    }

    @GetMapping("/ile")
    public String test(){
        System.out.println(categoryRepository.findAll());
        return "redirect:/worker";
    }
}
