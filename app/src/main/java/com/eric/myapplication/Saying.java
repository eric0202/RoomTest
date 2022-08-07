package com.eric.myapplication;

import androidx.annotation.NonNull;

public class Saying {
    private String author;
    private String content;

    public Saying(String author, String content) {
        this.author = author;
        this.content = content;
    }

    public Saying(){}

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @NonNull
    @Override
    public String toString() {
        return this.getClass()+
                ": " + "Author: "+ this.author+
                "Said: " + this.content;
    }
}
