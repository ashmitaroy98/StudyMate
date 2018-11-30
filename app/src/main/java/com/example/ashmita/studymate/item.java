package com.example.ashmita.studymate;

/**
 * Created by Ashmita on 2018-11-25.
 */

public class item {


    private String name, image, desc, time;

    public item() {
    }

    public item(String name, String image, String desc, String time) {
        this.name = name;
        this.image = image;
        this.desc = desc;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}