package com.BoostingWebsite.order.message;

public class SimpleMessageDtoSnapshot {
    private Long id;
    private String message;
    private Long authorId;
    private Long recipientId;

    public SimpleMessageDtoSnapshot(Long id, String message, Long authorId, Long recipientId) {
        this.id = id;
        this.message = message;
        this.authorId = authorId;
        this.recipientId = recipientId;
    }

    public Long getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public Long getRecipientId() {
        return recipientId;
    }
}
