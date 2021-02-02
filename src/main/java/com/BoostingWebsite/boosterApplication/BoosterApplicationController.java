package com.BoostingWebsite.boosterApplication;

import com.BoostingWebsite.order.enumeration.Region;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/booster-application")
class BoosterApplicationController {

    private final BoosterApplicationRepository boosterApplicationRepository;

    BoosterApplicationController(BoosterApplicationRepository boosterApplicationRepository) {
        this.boosterApplicationRepository = boosterApplicationRepository;
    }

    @GetMapping
    String boosterApplicationPage(Model model) {
        BoosterApplication boosterApplication = new BoosterApplication();
        model.addAttribute("boosterApplication", boosterApplication);
        model.addAttribute("regions", Region.values());
        return "booster-application/booster-application";
    }

    @PostMapping
    String boosterApplicationForm(@Valid @ModelAttribute("boosterApplication") BoosterApplication boosterApplication, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("regions", Region.values());
            return "booster-application/booster-application";
        }
        boosterApplicationRepository.save(boosterApplication);
        return "redirect:/";
    }

}