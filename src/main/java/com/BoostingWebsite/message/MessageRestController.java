package com.BoostingWebsite.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    private final JsonMessageHandler jsonMessageHandler;

    public MessageRestController(JsonMessageHandler jsonMessageHandler) {
        this.jsonMessageHandler = jsonMessageHandler;
    }

    @GetMapping("/sendMessage/singleConversation/Message")
    private String shareJSONWithConversation() {
        return jsonMessageHandler.createJsonFile().toJSONString();
    }

}
