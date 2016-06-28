package com.sopt.steam.main.model;

/**
 * Created by Min_Mac on 16. 5. 3..
 */
public class RecModel {

    public int id;
    public int user_id;
    public String rec_url;
    public String file_name;
    public String date;
    public String memo;

    public RecModel(int id, int user_id, String rec_url, String file_name, String date, String memo) {
        this.id = id;
        this.user_id = user_id;
        this.rec_url = rec_url;
        this.file_name = file_name;
        this.date = date;
        this.memo = memo;
    }
}
