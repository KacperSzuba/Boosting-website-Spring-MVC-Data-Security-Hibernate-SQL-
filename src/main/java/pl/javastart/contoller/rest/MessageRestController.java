package pl.javastart.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.manage.json.JsonHandler;
import pl.javastart.manage.json.JsonMessageHandler;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageRestController {

    @Autowired
    private JsonMessageHandler jsonMessageHandler;

    @Autowired
    private JsonHandler jsonHandler;

    @RequestMapping("/message/singleConversation/Message")
    private String shareJSONWithConversation(HttpServletRequest request) {
        return jsonMessageHandler.createJsonFile(request).toJSONString();
    }

    @RequestMapping("/NewOrder/Order")
    public String showOrderPage(){
        return jsonHandler.createJsonFile().toJSONString();
    }

}