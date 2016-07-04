package com.sopt.steam.afterFilter;

/**
 * Created by user on 2016-06-30.
 */
public class ListItem {
    String image;
    String name; // 스터디룸 이름
    String price; // 가격
    String time; // 시간
    String grade;
    int tempImage; // 기능 구현 전 Test를 위해 사용.

    public ListItem(String image, String name, String price, String time, String grade, int tempImage)
    {
        this.image = image;
        this.name = name;
        this.price = price;
        this.time = time;
        this.grade = grade;
        this.tempImage = tempImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTempImage() {
        return tempImage;
    }

    public void setTempImage(int tempImage) {
        this.tempImage = tempImage;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getTime() {
        return time;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}
