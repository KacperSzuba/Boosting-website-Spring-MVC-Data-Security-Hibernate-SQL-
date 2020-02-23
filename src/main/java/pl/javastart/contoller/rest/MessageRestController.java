package pl.javastart.contoller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.manage.json.JsonMessageHandler;

@RestController
public class MessageRestController {

    @Autowired
    private JsonMessageHandler jsonMessageHandler;

    @RequestMapping("/sendMessage/singleConversation/Message")
    private String shareJSONWithConversation() {
        return jsonMessageHandler.createJsonFile().toJSONString();
    }

}
