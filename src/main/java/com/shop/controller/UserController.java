package com.shop.controller;

import com.shop.model.Adress;
import com.shop.model.Order;
import com.shop.model.OrderItem;
import com.shop.model.User;
import com.shop.repository.OrderItemRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private OrderItemRepository orderItemRepository;
    private OrderRepository orderRepository;

    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }

    @Autowired
    public UserController(UserRepository userRepository,OrderItemRepository orderItemRepository,OrderRepository orderRepository){
        this.userRepository=userRepository;
        this.orderItemRepository=orderItemRepository;
        this.orderRepository=orderRepository;
    }


    @GetMapping("/login")
    public String login(){
        return "login";
    }



    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        model.addAttribute("adress",new Adress());
        return "registerForm";
    }

    @PostMapping("/register")
    public String addUser(@ModelAttribute @Valid User user, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "registerForm";
        else{
            userService.addWithDefaultRole(user);
            return "redirect:/";
        }
    }

    @GetMapping("/personalData")
    public String personalData(Principal principal,Model model){

        System.out.println(principal.getName());
        User user=userRepository.findByLogin(principal.getName());
        //List<OrderItem> orders=orderItemRepository.findByUser(user.getId());
        List<Order> orders=orderRepository.findByUser(user);
        System.out.println(orders);
        if(user.getAdress()==null){
            model.addAttribute("adress",new Adress());
        }else{
            Adress adress=user.getAdress();
            model.addAttribute("adress",adress);
        }
        model.addAttribute("orders",orders);
        model.addAttribute("user",user);
        return "personalData";
    }

    @GetMapping("/edit")
    public String updateUser(Principal principal,Model model){
        User user=userRepository.findByLogin(principal.getName());
        model.addAttribute("user",user);
        return "updateForm";
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable("id") Long id,@ModelAttribute @Valid User user, BindingResult bindingResult){
        User user1=userRepository.findById(id).get();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setEmail(user.getEmail());
        if(bindingResult.hasErrors()){
            user.setId(id);
            return "personalData";
        }else{
            userRepository.save(user1);
            return "redirect:/user/personalData";
        }
    }

}


