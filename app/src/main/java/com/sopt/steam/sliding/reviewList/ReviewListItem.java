package com.sopt.steam.sliding.reviewList;

/**
 * Created by user on 2016-07-03.
 */
public class ReviewListItem {
    String name;
    String date;
    String room;
    String content;

    public ReviewListItem(String name, String date, String room, String content)
    {
        this.name = name;
        this.date = date;
        this.room = room;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getRoom() {
        return room;
    }

    public String getContent() {
        return content;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
