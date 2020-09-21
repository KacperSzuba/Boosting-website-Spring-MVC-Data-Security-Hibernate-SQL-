package com.BoostingWebsite.order;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderRestController {

    private final JsonOrderHandler jsonOrderHandler;

    public OrderRestController(JsonOrderHandler jsonOrderHandler) {
        this.jsonOrderHandler = jsonOrderHandler;
    }

 //   @GetMapping("/order/Order")
//    public String showOrderPage(){
 //       return jsonOrderHandler.createJsonFile().toJSONString();
 //   }

}
