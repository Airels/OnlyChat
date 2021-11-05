package com.example.onlychat.model;

import com.example.onlychat.model.data.entities.UsersEntity;

public class User {

    private int id;
    private String username;
    private String password;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UsersEntity entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.password = entity.getPassword();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
