package com.example.onlychat.model;

import com.example.onlychat.model.data.entities.MessagesEntity;

import java.sql.Timestamp;

public class Message {

    private final String author;
    private final String content;
    private final Timestamp timestamp;

    public Message(User author, String content, long timestamp) {
        this.author = author.getUsername();
        this.content = content;
        this.timestamp = new Timestamp(timestamp);
    }

    public Message(MessagesEntity e) {
        this.author = e.getAuthor();
        this.content = e.getContent();
        this.timestamp = e.getTimestamp();
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp.getTime();
    }
}
