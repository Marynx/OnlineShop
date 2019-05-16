package com.shop.controller;

import com.shop.model.Item;
import com.shop.model.Order;
import com.shop.model.OrderItem;
import com.shop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }


    @GetMapping("/{id}")
    public String orderDetails(@PathVariable("id") Long id, Model model){
        Order order = orderRepository.findById(id).get();
        Set<OrderItem> items= order.getOrderItems();
        model.addAttribute("items",items);
        return "orderDetails";
    }


}

