package com.shop.controller;

import com.shop.model.Adress;
import com.shop.model.User;
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

    @GetMapping("/getuser")
    public String jol(Model model){
        User user=userRepository.findById(1L).get();
        System.out.println(user);

        return "/";
    }

    @GetMapping("/personalData")
    public String personalData(HttpServletRequest request,Model model){
        String user= request.getUserPrincipal().getName();
        System.out.println(user);


        //        User user=userRepository.findByLogin(principal.getName());
//        if(user.getAdress()==null){
//            model.addAttribute("adress",new Adress());
//        }else{
//            Adress adress=user.getAdress();
//            model.addAttribute("adress",adress);
//        }
//        System.out.println(user);
//        model.addAttribute("user",user);
        return "personalData";
    }

}


