package com.BoostingWebsite.message;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Component;

@Component
class JsonMessageHandler {

    private final MessageRepository messageRepository;
    JsonMessageHandler(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    JSONArray createJsonFile2(Long id) {
        JSONArray jsonArray = new JSONArray();
        for (Message message : messageRepository.list(id)) {
            JSONObject record = new JSONObject();
            record.put("id",message.getId());
            record.put("sendMessage",message.getMessage());
            record.put("messageSender",message.getAuthor().getId());
            record.put("recipientOfTheMessage",message.getRecipient().getId());
            record.put("date",message.getDate().toString());
            jsonArray.add(record);
        }
        return jsonArray;
    }
}
