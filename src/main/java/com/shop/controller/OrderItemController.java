package com.shop.controller;

import com.shop.model.*;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class OrderItemController {
    private ItemRepository itemRepository;
    private OrderItemRepository orderItemRepository;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderItemController(ItemRepository itemRepository,OrderItemRepository orderItemRepository,
                               UserRepository userRepository, OrderRepository orderRepository){
        this.itemRepository=itemRepository;
        this.orderItemRepository=orderItemRepository;
        this.userRepository=userRepository;
        this.orderRepository=orderRepository;
    }

    @PostMapping("/{item_id}")
    public String addToCart(Principal principal,@PathVariable(name = "item_id") Long item_id, @ModelAttribute @Valid OrderItem orderItem){
        Item item=itemRepository.findById(item_id).get();
        User user=userRepository.findByLogin(principal.getName());
        item.setQuantity(item.getQuantity() - orderItem.getItemQuantity());
        itemRepository.save(item);
        List<Order>userOrders=orderRepository.findByUser(user);
        int orderExists=0;
        for(Order o: userOrders){
            if(o.getOrderStatus().equals(OrderStatus.NOT_ACCEPTED.getStatus())){
                orderExists++;
                break;
            }
        }
        if(userOrders.size()==0 || orderExists==0){
            Order order=new Order();
            order.setOrderDate(new Date(System.currentTimeMillis()));
            order.setUser(user);
            order.setPrice(item.getPrice()*orderItem.getItemQuantity());
            order.setOrderStatus(OrderStatus.NOT_ACCEPTED.getStatus());
            orderRepository.save(order);
            orderItem.setOrder(order);
        }else {
            for (Order o : userOrders) {
                if (o.getOrderStatus().equals(OrderStatus.NOT_ACCEPTED.getStatus())) {
                    orderItem.setOrder(o);
                    break;
                }
            }
        }
        orderItem.setItem(item);
        orderItemRepository.save(orderItem);
        return "redirect:/item/"+item_id;
    }

    @GetMapping("/my")
    public String myCart(Principal principal, Model model){
        User user = userRepository.findByLogin(principal.getName());
        List<OrderItem> orderItems=orderItemRepository.findByCart(user.getId());
        double totalPrice=0;
        for(OrderItem items: orderItems){
            totalPrice=totalPrice+(items.getItemQuantity()*items.getItem().getPrice());
        }
        model.addAttribute("orderItems",orderItems);
        model.addAttribute("totalPrice",totalPrice);
        model.addAttribute("user",user);
        return "cart";
    }

    @GetMapping("/delete/{item_id}")
    public String deleteFromCart(Principal principal,@PathVariable(name = "item_id") Long item_id){
        OrderItem oi=orderItemRepository.findById(item_id).get();
        orderItemRepository.delete(oi);
        return "redirect:/cart/my";
    }

    @GetMapping("/delete/all")
    public String deleteAll(Principal principal,Model model){
        User user=userRepository.findByLogin(principal.getName());
        List<OrderItem> usersCart=orderItemRepository.findByCart(user.getId());
        for(OrderItem oi:usersCart){
            orderItemRepository.delete(oi);
        }
        return "redirect:/cart/my";
    }

}
