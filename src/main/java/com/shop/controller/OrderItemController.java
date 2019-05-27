package com.shop.controller;

import com.shop.model.Item;
import com.shop.model.Order;
import com.shop.model.OrderItem;
import com.shop.model.User;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class OrderItemController {
    private ItemRepository itemRepository;
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;

    @Autowired
    public OrderItemController(ItemRepository itemRepository,OrderItemRepository orderItemRepository,UserRepository userRepository){
        this.itemRepository=itemRepository;
        this.orderItemRepository=orderItemRepository;
        this.userRepository=userRepository;
    }

    @PostMapping("/{item_id}")
    public String addToCart(@PathVariable(name = "item_id") Long item_id, @ModelAttribute @Valid OrderItem orderItem){
        Item item=itemRepository.findById(item_id).get();
        orderItem.setItem(item);
        orderItemRepository.save(orderItem);
        return "redirect:/product/"+item_id;
    }

    @GetMapping("/my")
    public String myCart(Principal principal, Model model){
        User user = userRepository.findByLogin(principal.getName());
        List<OrderItem> orderItems=orderItemRepository.findByUser(user.getId());
        double totalPrice=0;
        for(OrderItem items: orderItems){
            totalPrice=totalPrice+(items.getItemQuantity()*items.getItem().getPrice());
        }
        model.addAttribute("orderItems",orderItems);
        model.addAttribute("totalPrice",totalPrice);
        return "cart";
    }

}
