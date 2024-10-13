package com.example.dogfoodapp;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "admins")
public class Admin {
    @PrimaryKey(autoGenerate = true)
    private int id;

    private String email;
    private String password;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
