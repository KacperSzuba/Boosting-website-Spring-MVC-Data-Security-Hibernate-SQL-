package com.BoostingWebsite.order.preview;

import com.BoostingWebsite.message.Message;

import java.util.ArrayList;
import java.util.List;

class ChatMessageMapper {
    static List<MessageDTO> mapMessagesToChatDTOs(List<Message> chatMessages) {
        List<MessageDTO> messageDTOS = new ArrayList<MessageDTO>();

        for(Message chatMessage : chatMessages) {
            messageDTOS.add(new MessageDTO(chatMessage.getMessage(), chatMessage.getAuthor().getUsername(), chatMessage.getRecipient().getUsername()));
        }

        return messageDTOS;
    }
}
