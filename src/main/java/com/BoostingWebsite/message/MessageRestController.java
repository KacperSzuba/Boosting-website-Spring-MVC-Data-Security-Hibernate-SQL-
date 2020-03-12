package com.BoostingWebsite.message;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageRestController {

    private final JsonMessageHandler jsonMessageHandler;

    public MessageRestController(JsonMessageHandler jsonMessageHandler) {
        this.jsonMessageHandler = jsonMessageHandler;
    }

    @GetMapping("/message/list")
    private String shareJSONWithConversation2(@RequestParam Long id) {
        return jsonMessageHandler.createJsonFile2(id).toJSONString();
    }

}
