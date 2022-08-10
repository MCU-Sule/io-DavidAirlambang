package com.pertemuan09.teori09.entity;

public class User {

    private String user;
    private String comment;

    @Override
    public String toString() {
        return user + " - " + comment;
    }

    public User(String user, String comment) {
        this.user = user;
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}


