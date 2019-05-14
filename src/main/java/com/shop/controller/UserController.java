package com.shop.controller;

import com.shop.model.Adress;
import com.shop.model.User;
import com.shop.repository.AdressRepository;
import com.shop.repository.UserRepository;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class UserController {
    private UserService userService;
    private UserRepository userRepository;
    private AdressRepository adressRepository;

    @Autowired
    public void setUserService(UserService userService){
        this.userService=userService;
    }

    @Autowired
    public UserController(UserRepository userRepository){
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

    @GetMapping("/personalData")
    public String personalData(Principal principal,Model model){


        User user=userRepository.findByLogin(principal.getName());
        if(user.getAdress()==null){
            model.addAttribute("adress",new Adress());
        }else{
            Adress adress=user.getAdress();
            model.addAttribute("adress",adress);
        }
        System.out.println(user);
        model.addAttribute("user",user);
        return "personalData";
    }

    @GetMapping("/edit")
    public String updateUser(Principal principal,Model model){
        User user=userRepository.findByLogin(principal.getName());
        model.addAttribute("user",user);
        return "updateForm";
    }

}


