package com.java.lowongan.lowongan_server.service;

public class Notifikasi {

    private String title;
    private String body;
    private Long senderId;
    private Long receiverId;

    public Notifikasi(String title, String body, Long senderId, Long receiverId) {
        this.title = title;
        this.body = body;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}