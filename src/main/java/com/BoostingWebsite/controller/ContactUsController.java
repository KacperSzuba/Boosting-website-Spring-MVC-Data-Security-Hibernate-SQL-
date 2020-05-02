package com.BoostingWebsite.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contact-us")
public class ContactUsController {

    @GetMapping
    public String showContactUsPage(){
        return "contact-us";
    }
}
