package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.CategoryRepository;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {

    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ProducerRepository producerRepository;

    @Autowired
    public WorkerController(OrderRepository orderRepository,ItemRepository itemRepository,
                            CategoryRepository categoryRepository,ProducerRepository producerRepository){
        this.orderRepository=orderRepository;
        this.itemRepository=itemRepository;
        this.categoryRepository=categoryRepository;
        this.producerRepository=producerRepository;
    }


    @GetMapping("")
    public String home(Model model){
        List<Order> orders= (List<Order>) orderRepository.findAll();
        List<Item> items= (List<Item>) itemRepository.findAll();
        List<Category> categories= (List<Category>) categoryRepository.findAll();
        List<Producer> producers= (List<Producer>) producerRepository.findAll();
        model.addAttribute("product",new Product());
        model.addAttribute("item",new Item());
        model.addAttribute("producer",new Producer());
        model.addAttribute("orders",orders);
        model.addAttribute("items",items);
        model.addAttribute("categories",categories);
        model.addAttribute("producers",producers);
        return "workerPane";
    }

}
