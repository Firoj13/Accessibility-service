package com.example.accessibilityservice.adapter;

public class MessageList {

    private String courseMessage;
    private String number;

    public MessageList(String courseMessage, String number) {
        this.courseMessage = courseMessage;
        this.number = number;
    }

    public String getCourseMessage() {
        return courseMessage;
    }

    public void setCourseMessage(String courseMessage) {
        this.courseMessage = courseMessage;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
