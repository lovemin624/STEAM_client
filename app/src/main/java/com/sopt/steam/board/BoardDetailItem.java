package com.sopt.steam.board;

/**
 * Created by user on 2016-07-01.
 */
public class BoardDetailItem {
    String tv_name;
    String tv_content;

    public BoardDetailItem(String tv_name, String tv_content) {
        this.tv_name = tv_name;
        this.tv_content = tv_content;
    }

    public String getTv_name() {
        return tv_name;
    }

    public String getTv_content() {
        return tv_content;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }

    public void setTv_content(String tv_content) {
        this.tv_content = tv_content;
    }
}

