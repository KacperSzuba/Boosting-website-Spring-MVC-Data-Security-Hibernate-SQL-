package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDtoSnapshot;

import java.time.LocalDateTime;

class Message {
    private Long id;
    private String message;
    private SimpleUserDtoSnapshot author;
    private SimpleUserDtoSnapshot recipient;
    private LocalDateTime date;

    private Message(Long id, String message, SimpleUserDtoSnapshot author, SimpleUserDtoSnapshot recipient, LocalDateTime date) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.recipient = recipient;
        this.date = date;
    }

    static Message restore(MessageSnapshot snapshot){
        return new Message(
                snapshot.getId(),
                snapshot.getMessage(),
                snapshot.getAuthor(),
                snapshot.getRecipient(),
                snapshot.getDate());
    }

    MessageSnapshot getSnapshot(){
        return new MessageSnapshot(id, message, author, recipient, date);
    }
}
