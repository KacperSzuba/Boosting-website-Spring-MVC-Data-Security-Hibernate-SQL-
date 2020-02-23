package pl.javastart.contoller.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.javastart.manage.json.JsonMessageHandler;

@RestController
public class MessageRestController {

    private final JsonMessageHandler jsonMessageHandler;

    public MessageRestController(JsonMessageHandler jsonMessageHandler) {
        this.jsonMessageHandler = jsonMessageHandler;
    }

    @RequestMapping("/sendMessage/singleConversation/Message")
    private String shareJSONWithConversation() {
        return jsonMessageHandler.createJsonFile().toJSONString();
    }

}
