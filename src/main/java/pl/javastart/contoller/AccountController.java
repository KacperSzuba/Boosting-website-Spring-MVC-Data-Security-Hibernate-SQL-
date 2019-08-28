package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {


    @RequestMapping
    public String showAccountPage(){
        return "account";
    }

}
