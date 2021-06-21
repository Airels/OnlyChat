package com.example.onlychat.model;

public class Message {

    private final String author;
    private final String content;
    private final long timestamp;

    public Message(User author, String content, long timestamp) {
        this.author = author.getUsername();
        this.content = content;
        this.timestamp = timestamp;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
