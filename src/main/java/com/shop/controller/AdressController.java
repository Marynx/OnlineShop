package com.shop.controller;

import com.shop.model.Adress;
import com.shop.model.User;
import com.shop.repository.AdressRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/adress")
public class AdressController {
    private UserRepository userRepository;
    private AdressRepository adressRepository;

    @Autowired
    public AdressController(UserRepository userRepository,AdressRepository adressRepository){
        this.userRepository=userRepository;
        this.adressRepository=adressRepository;
    }

//
//    @GetMapping("/")
//    public String updateAdress(Principal principal, Model model){
//        User user=userRepository.findByLogin(principal.getName());
//        Adress adress=user.getAdress();
//        System.out.println(adress);
//        model.addAttribute("adress",adress);
//        return "updateForm";
//    }

    @PostMapping("")
    public String addAdress(Principal principal, @ModelAttribute @Valid Adress adress, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "redirect:/user/personalData";
        else{
            User user =userRepository.findByLogin(principal.getName());
            adressRepository.save(adress);
            user.setAdress(adress);
            userRepository.save(user);
            return "redirect:/user/personalData";
        }
    }

    @GetMapping("/edit")
    public String updateAddress(Principal principal, Model model){
        User user=userRepository.findByLogin(principal.getName());
        Adress adress=user.getAdress();
        System.out.println(adress);
        model.addAttribute("adress",adress);
        return "updateForm";
    }

    @PostMapping("/edit/{id}")
    public String updateAddress(@PathVariable("id") Long id, @Valid Adress adress, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            adress.setId(id);
            return "updateForm";
        }else{
            System.out.println(adress);
           // Adress adress1=adressRepository.findById(adress.getId()).get();

            adressRepository.save(adress);
            return "redirect:/user/personalData";
        }
    }
}
