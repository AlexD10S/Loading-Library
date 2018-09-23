package com.alexd10s.wallofimages.model;

/**
 * Created by alex on 22/09/2018.
 */

public class User {
    private String username;
    private String name;
    private Image profile_image;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(Image profile_image) {
        this.profile_image = profile_image;
    }
}
