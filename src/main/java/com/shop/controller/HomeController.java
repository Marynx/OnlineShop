package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

        @RequestMapping("/")
        public String home(){
            return "index1";
        }

        @RequestMapping("/index")
        public String homix(){
            return "index";
        }



}
