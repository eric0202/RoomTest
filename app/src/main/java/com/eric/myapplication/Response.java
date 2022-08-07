package com.eric.myapplication;

import androidx.annotation.NonNull;

public class Response {
    private int code;
    private Saying saying;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Saying getSaying() {
        return saying;
    }

    public void setSaying(Saying saying) {
        this.saying = saying;
    }

    public Response(int code, Saying saying) {
        this.code = code;
        this.saying = saying;
    }

    public Response() {
    }

    @NonNull
    @Override
    public String toString() {
        return this.getClass()+
                ": code: "+ this.code+
                " saying: "+ this.saying;
    }
}
