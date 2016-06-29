package com.sopt.steam.profile.model;


public class UserProfile {

    public final static int SIBLING_0 = 0, SIBLING_1 = 1, SIBLING_2 = 2;
    public final static int DESIRED_TIME_10 = 10, DESIRED_TIME_20 = 20, DESIRED_TIME_30 = 30;
    public final static String RANK_A = "A", RANK_B = "B", RANK_C = "C";
    public final static int RANK_PAY_A = 15000, RANK_PAY_B = 12000, RANK_PAY_C = 10000;
    public final static int SIBLING_PAY_0 = 0, SIBLING_PAY_1 = 3000, SIBLING_PAY_2 = 6000;

    public String user_id;
    public String passwd;
    public String name;

    public UserProfile(String user_id, String passwd, String name) {
        this.user_id = user_id;
        this.passwd = passwd;
        this.name = name;
    }
}