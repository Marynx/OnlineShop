package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.*;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/worker")
public class WorkerController {


    private OrderRepository orderRepository;
    private ItemRepository itemRepository;
    private CategoryRepository categoryRepository;
    private ProducerRepository producerRepository;
    private ProductRepository productRepository;

    @Autowired
    public WorkerController(OrderRepository orderRepository,ItemRepository itemRepository,
                            CategoryRepository categoryRepository,ProducerRepository producerRepository,
                            ProductRepository productRepository){
        this.orderRepository=orderRepository;
        this.itemRepository=itemRepository;
        this.categoryRepository=categoryRepository;
        this.producerRepository=producerRepository;
        this.productRepository=productRepository;
    }


    @GetMapping("")
    public String home(Model model){
        List<Order> orders= (List<Order>) orderRepository.findAll();
        List<Item> items= (List<Item>) itemRepository.findAll();
        List<Category> categories= (List<Category>) categoryRepository.findAll();
        List<Producer> producers= (List<Producer>) producerRepository.findAll();
        List<Product> products= (List<Product>) productRepository.findAll();
        List<OrderStatus> orderStatuses=new ArrayList<>(Arrays.asList(OrderStatus.values()));
        model.addAttribute("product",new Product());
        model.addAttribute("item",new Item());
        model.addAttribute("producer",new Producer());
        model.addAttribute("orders",orders);
        model.addAttribute("items",items);
        model.addAttribute("categories",categories);
        model.addAttribute("producers",producers);
        model.addAttribute("products",products);
        model.addAttribute("orderStatuses",orderStatuses);
        return "workerPane";
    }



}
