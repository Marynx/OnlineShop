package com.shop.controller;

import com.shop.model.Adress;
import com.shop.model.User;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {
    private UserService userService;
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserService userService, UserRepository userRepository){
        this.userService=userService;
        this.userRepository=userRepository;
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

}


