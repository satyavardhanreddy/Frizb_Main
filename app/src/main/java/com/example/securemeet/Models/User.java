package com.example.securemeet.Models;

public class User {
    private String id;
    private String email;
    private String name;
    private String password;
    private String bio;
    private String imageurl;

    public User(String id, String email, String name, String password, String bio, String imageurl) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.bio = bio;
        this.imageurl = imageurl;
    }

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}