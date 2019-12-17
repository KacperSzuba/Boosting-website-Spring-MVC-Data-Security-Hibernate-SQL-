package pl.javastart.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.manage.JsonHandler;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MessageRestController {

    @Autowired
    private JsonHandler jsonHandler;

    @RequestMapping("/message/singleConversation/Message")
    private String shareJSONWithConversation(HttpServletRequest request) {
        return jsonHandler.createJsonFile(request).toJSONString();
    }
}