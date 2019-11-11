package com.casicloud.model;

public class Message {

    private long id;
    private String topic;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Message(long id, String topic, String content) {
        this.id = id;
        this.topic = topic;
        this.content = content;
    }

    public Message() {
    }
}
