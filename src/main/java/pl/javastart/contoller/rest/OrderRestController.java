package pl.javastart.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.javastart.manage.json.JsonOrderHandler;

@Controller
public class OrderRestController {

    @Autowired
    private JsonOrderHandler jsonOrderHandler;
    
    @RequestMapping("/order/Order")
    public String showOrderPage(){
        return jsonOrderHandler.createJsonFile().toJSONString();
    }

}
