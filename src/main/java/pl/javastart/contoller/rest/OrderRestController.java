package pl.javastart.contoller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.manage.json.JsonOrderHandler;

@RestController
public class OrderRestController {

    private final JsonOrderHandler jsonOrderHandler;

    public OrderRestController(JsonOrderHandler jsonOrderHandler) {
        this.jsonOrderHandler = jsonOrderHandler;
    }

    @RequestMapping("/order/Order")
    public String showOrderPage(){
        return jsonOrderHandler.createJsonFile().toJSONString();
    }

}
