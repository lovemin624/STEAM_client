package com.sopt.steam.join.model;

public class JoinUser {

    public String user_id;
    public String passwd;
    public String name;

    public JoinUser(String user_id, String passwd, String name) {
        this.user_id = user_id;
        this.passwd = passwd;
        this.name = name;
    }
}
