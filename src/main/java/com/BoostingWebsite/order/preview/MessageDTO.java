package com.BoostingWebsite.order.preview;

public class MessageDTO {
    private String content;
    private String senderName;
    private String recipientName;

    public MessageDTO(String content, String senderName, String recipientName) {
        this.content = content;
        this.senderName = senderName;
        this.recipientName = recipientName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }
}
