package com.shop.controller;

import com.shop.model.Adress;
import com.shop.model.User;
import com.shop.repository.AdressRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class AdressController {
    private UserRepository userRepository;
    private AdressRepository adressRepository;

    @Autowired
    public AdressController(UserRepository userRepository,AdressRepository adressRepository){
        this.userRepository=userRepository;
        this.adressRepository=adressRepository;
    }

    @PostMapping("/adress")
    public String addAdress(Principal principal, @ModelAttribute @Valid Adress adress, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "personalData";
        else{
            User user =userRepository.findByLogin(principal.getName());
            adressRepository.save(adress);
            user.setAdress(adress);
            userRepository.save(user);
            return "redirect:/";
        }
    }
}
