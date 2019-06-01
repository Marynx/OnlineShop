package com.shop.controller;

import com.shop.model.Producer;
import com.shop.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/producer")
public class ProducerController {

    private ProducerRepository producerRepository;

    @Autowired
    public ProducerController(ProducerRepository producerRepository){
        this.producerRepository=producerRepository;
    }

    @PostMapping("/add")
    public String addProducer(@ModelAttribute @Valid Producer producer){
        producerRepository.save(producer);
        return "redirect:/worker";
    }
}
