package com.shop.controller;

import com.shop.model.Producer;
import com.shop.model.Role;
import com.shop.model.User;
import com.shop.repository.ProducerRepository;
import com.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private ProducerRepository producerRepository;
    private UserRepository userRepository;

    @Autowired
    public ProducerController(ProducerRepository producerRepository,UserRepository userRepository){
        this.producerRepository=producerRepository;
        this.userRepository=userRepository;
    }

    @PostMapping("/add")
    public String addProducer(@ModelAttribute @Valid Producer producer, BindingResult bindingResult, Principal principal){
        if(bindingResult.hasErrors()){
            return "adminPane";
        }
        User user =userRepository.findByLogin(principal.getName());
        List<Role> roles= (List<Role>) user.getRoles();
        producerRepository.save(producer);
        for(Role r: roles){
            if(r.getRole().equals("ROLE_ADMIN")){
                return "redirect:/admin";
            }
        }

        return "redirect:/worker";
    }
}
