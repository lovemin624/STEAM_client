package com.sopt.steam.profile.model;


public class UserProfile {

    public String user_id;
    public String passwd;
    public String name;

    public UserProfile(String user_id, String passwd, String name) {
        this.user_id = user_id;
        this.passwd = passwd;
        this.name = name;
    }
}