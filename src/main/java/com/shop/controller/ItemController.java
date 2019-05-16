package com.shop.controller;

import com.shop.model.Item;
import com.shop.repository.ItemRepository;
import com.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/product")
public class ItemController {

    private ProductRepository productRepository;
    private ItemRepository itemRepository;

    @Autowired
    public ItemController(ProductRepository productRepository,ItemRepository itemRepository){
        this.productRepository=productRepository;
        this.itemRepository=itemRepository;
    }

    @GetMapping("/{id}")
    public String showProduct(@PathVariable("id") Long id, Model model){
        Item item=itemRepository.findById(id).get();
        String path=item.getImages().iterator().next().getPath();
        model.addAttribute("item",item);
        model.addAttribute("path",path);
        return "product";
    }
}
