package com.BoostingWebsite.order.message;

import com.BoostingWebsite.account.SimpleUserDtoSnapshot;

import java.time.LocalDateTime;

class MessageSnapshot {
    private Long id;
    private String message;
    private SimpleUserDtoSnapshot author;
    private SimpleUserDtoSnapshot recipient;
    private LocalDateTime date;

    protected MessageSnapshot(){}

    MessageSnapshot(Long id, String message, SimpleUserDtoSnapshot author, SimpleUserDtoSnapshot recipient, LocalDateTime date) {
        this.id = id;
        this.message = message;
        this.author = author;
        this.recipient = recipient;
        this.date = date;
    }

    Long getId() {
        return id;
    }

    String getMessage() {
        return message;
    }

    SimpleUserDtoSnapshot getAuthor() {
        return author;
    }

    SimpleUserDtoSnapshot getRecipient() {
        return recipient;
    }

    LocalDateTime getDate() {
        return date;
    }
}
