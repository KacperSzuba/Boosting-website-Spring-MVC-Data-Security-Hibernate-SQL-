package com.BoostingWebsite.order.message;

public class SimpleMessageDto {
    private Long id;
    private String message;
    private Long authorId;
    private Long recipientId;

    private SimpleMessageDto(Long id, String message, Long authorId, Long recipientId) {
        this.id = id;
        this.message = message;
        this.authorId = authorId;
        this.recipientId = recipientId;
    }

    public static SimpleMessageDto restore(SimpleMessageDtoSnapshot snapshot){
        return new SimpleMessageDto(snapshot.getId(), snapshot.getMessage(), snapshot.getAuthorId(), snapshot.getRecipientId());
    }

    public SimpleMessageDtoSnapshot getSnapshot(){
        return new SimpleMessageDtoSnapshot(id, message, authorId, recipientId);
    }
}
