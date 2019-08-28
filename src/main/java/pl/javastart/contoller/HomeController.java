package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {


    @RequestMapping("/")
    public String showHomePage(){


        return "home";
    }

    @RequestMapping("/secure")
    public String showSecuredPage(){
        return "secure";
    }
}
