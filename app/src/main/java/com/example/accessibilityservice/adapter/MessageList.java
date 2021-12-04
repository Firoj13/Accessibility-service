package com.example.accessibilityservice.adapter;

public class MessageList {

    private String courseMessage;

    public MessageList(String courseMessage) {
        this.courseMessage = courseMessage;
    }


    public String getCourseMessage() {
        return courseMessage;
    }

    public void setCourseMessage(String courseMessage) {
        this.courseMessage = courseMessage;
    }
}
