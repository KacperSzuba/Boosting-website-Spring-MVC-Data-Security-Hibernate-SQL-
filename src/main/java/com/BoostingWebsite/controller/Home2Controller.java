package com.BoostingWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home2Controller {

    @RequestMapping("/home2")
    public String showHome2Page(){
        return "home2";
    }
}
