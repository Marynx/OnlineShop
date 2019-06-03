package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/order")
public class OrderController {
    private OrderRepository orderRepository;
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository,OrderItemRepository orderItemRepository,UserRepository userRepository){
        this.orderRepository=orderRepository;
        this.orderItemRepository=orderItemRepository;
        this.userRepository=userRepository;
    }


    @GetMapping("/{id}")
    public String orderDetails(@PathVariable("id") Long id, Model model){
        Order order = orderRepository.findById(id).get();
        Set<OrderItem> items= order.getOrderItems();
        model.addAttribute("items",items);
        return "orderDetails";
    }

    @GetMapping("/add")
    public String addOrder(Principal principal){
        User user=userRepository.findByLogin(principal.getName());
        Order order=orderRepository.findByOrderStatusAndUser(OrderStatus.NOT_ACCEPTED.getStatus(),user);
        List<OrderItem> orderItems=orderItemRepository.findByCart(user.getId());
        double totalPrice=0;
        for(OrderItem oi : orderItems){
            totalPrice+=oi.getItemQuantity()*oi.getItem().getPrice();
        }
        order.setOrderStatus(OrderStatus.IN_PROGRESS.getStatus());
        order.setPrice(totalPrice);
        orderRepository.save(order);
        return "redirect:/cart/my";
    }

    @PostMapping("/status/{id}/{status}")
    public String changeStatus(@PathVariable("id") Long id,@PathVariable("status") String status){
        Order order=orderRepository.findById(id).get();
        order.setOrderStatus(status);
        orderRepository.save(order);
        return "redirect:/worker";
    }

}

