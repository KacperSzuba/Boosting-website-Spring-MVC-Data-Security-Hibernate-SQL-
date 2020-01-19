package pl.javastart.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.manage.json.JsonOrderHandler;

@RestController
public class OrderRestController {

    @Autowired
    private JsonOrderHandler jsonOrderHandler;

    @RequestMapping("/order/Order")
    public String showOrderPage(){
        return jsonOrderHandler.createJsonFile().toJSONString();
    }

}
