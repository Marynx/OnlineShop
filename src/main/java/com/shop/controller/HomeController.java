package com.shop.controller;

import com.shop.model.Item;
import com.shop.model.OrderItem;
import com.shop.model.User;
import com.shop.repository.ItemRepository;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class HomeController {

        private ItemRepository itemRepository;
        private OrderItemRepository orderItemRepository;
        private UserRepository userRepository;

        @Autowired
        public HomeController(ItemRepository itemRepository,OrderItemRepository orderItemRepository,UserRepository userRepository){
            this.itemRepository=itemRepository;
            this.orderItemRepository=orderItemRepository;
            this.userRepository=userRepository;
        }

        @RequestMapping("/")
        public String home(Principal principal,Model model)
        {

            if(principal!=null){
                User user=userRepository.findByLogin(principal.getName());
                List<OrderItem>orderItems= orderItemRepository.findByCart(user.getId());
                model.addAttribute("orderItems",orderItems);
            }
            List<Item> items= (List<Item>) itemRepository.findAll();
            model.addAttribute("items",items);
            return "index1";
        }

        @RequestMapping("/index")
        public String homix(){
            return "index";
        }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginSuccess(Authentication authentication){
            if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
               return  "redirect:/user/personalData";
            }else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
                return "redirect:/admin";
            }else if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_WORKER"))){
                return "redirect:/worker";
            }
            return "redirect:/";
    }



}
