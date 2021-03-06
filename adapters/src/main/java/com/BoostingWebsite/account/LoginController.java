package com.BoostingWebsite.account;

import com.BoostingWebsite.utils.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
class LoginController extends BaseController {

    @GetMapping
    String loginPage(@RequestParam(value = "error", required = false) String error, Model model) {

        if(error != null) {
             model.addAttribute("errorMessage", "Username or Password is incorrect!");
        }

        return "account/login";
    }

}
