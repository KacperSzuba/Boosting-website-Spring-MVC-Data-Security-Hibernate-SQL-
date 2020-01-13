package pl.javastart.contoller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@PropertySource(value = "classpath:test.properties")
public class HomeController {
    Environment environment;

    @Value("${test}")
    private String test;

    @RequestMapping("/")
    public String showHomePage(){
        System.out.println(test);
        return "jsp/home";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        HttpSession httpSession = request.getSession();
        httpSession.invalidate();
        return "jsp/home";
    }
}