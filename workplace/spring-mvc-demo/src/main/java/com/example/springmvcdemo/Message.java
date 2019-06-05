package com.example.springmvcdemo;

import java.util.ArrayList;
import java.util.List;

public class Message {
    private String content;
    private String sender;
    private List<String> receiver;

    public Message(){

    }

    public Message(String content, String sender, List<String> receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }
    public Message(String content, String sender) {
        this.content = content;
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public List<String> getReceiver() {
        return receiver;
    }

    public void setReceiver(List<String> receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "Message{" +
                "content='" + content + '\'' +
                ", sender='" + sender + '\'' +
                ", receiver=" + receiver +
                '}';
    }
}
