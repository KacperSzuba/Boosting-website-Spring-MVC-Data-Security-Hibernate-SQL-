package pl.javastart.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/booster")
public class BoosterController {

    @RequestMapping
    public String showBoosterPage(){
        return "jsp/boosterPages/booster";
    }
}
