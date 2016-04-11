package com.play.eldarbabayev2.easylearning.models;


public class Chat {

    private String content;
    private String author;

    public Chat() {
    }

    public Chat(String content, String author) {
        this.content = content;
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }



}
