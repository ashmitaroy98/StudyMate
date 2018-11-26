package com.example.ashmita.studymate;

/**
 * Created by Ashmita on 2018-11-25.
 */

public class item {


    String profileName;
    int profilePhoto;
    String description;
    String time;


    public item() {
    }

    public item(String profileName,  String description,String ETtime,int profilePhoto) {
        this.profileName = profileName;
        this.profilePhoto = profilePhoto;

        this.description = description;
        this.time = ETtime;
    }


    public String getProfileName() {
        return profileName;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }


    public String getDescription()
    {
        return description;
    }

    public String getETtime(){
        return time;
    }


    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public void setETtime(String ETtime){
        this.time = ETtime;
    }

    public  void setDescription(String description){
        this.description = description;
    }
}
