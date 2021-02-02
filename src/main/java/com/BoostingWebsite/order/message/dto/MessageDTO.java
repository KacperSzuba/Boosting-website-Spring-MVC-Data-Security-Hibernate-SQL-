package com.BoostingWebsite.order.message.dto;

public class MessageDTO {
    public static Builder builder(){
        return new Builder();
    }

    private final String content;
    private final String senderName;
    private final String recipientName;

    private MessageDTO(Builder builder) {
        content = builder.content;
        senderName = builder.senderName;
        recipientName = builder.recipientName;
    }

    public String getContent() {
        return content;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public static class Builder{
        private String content;
        private String senderName;
        private String recipientName;

        private Builder(){}

        public MessageDTO build(){
            return new MessageDTO(this);
        }

        public Builder withContent(String content) {
            this.content = content;
            return this;
        }

        public Builder withSenderName(String senderName) {
            this.senderName = senderName;
            return this;
        }

        public Builder withRecipientName(String recipientName) {
            this.recipientName = recipientName;
            return this;
        }
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "content='" + content + '\'' +
                ", senderName='" + senderName + '\'' +
                ", recipientName='" + recipientName + '\'' +
                '}';
    }
}
