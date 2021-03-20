package com.BoostingWebsite.boosterApplication;

import com.BoostingWebsite.boosterApplication.dto.BoosterApplicationDto;
import com.BoostingWebsite.utils.BaseController;
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
class BoosterApplicationController extends BaseController {
    private final BoosterApplicationFacade facade;

    BoosterApplicationController(BoosterApplicationFacade facade) {
        this.facade = facade;
    }

    @GetMapping
    String boosterApplicationPage(Model model) {
        BoosterApplicationDto boosterApplicationDto = new BoosterApplicationDto();
        model.addAttribute("boosterApplicationDto", boosterApplicationDto);
        model.addAttribute("regions", Region.values());
        return "booster-application/booster-application";
    }

    @PostMapping
    String boosterApplicationForm(@Valid @ModelAttribute("boosterApplicationDto") BoosterApplicationDto boosterApplicationDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("regions", Region.values());
            return "booster-application/booster-application";
        }

        facade.save(boosterApplicationDto);
        return "redirect:/";
    }

}