package com.csci5308.groupme.course.student.model;

public class Student {

    private String userName;
    private String bannerID;

    public Student(String userName, String bannerID) {
        this.userName = userName;
        this.bannerID = bannerID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getBannerID() {
        return bannerID;
    }

    public void setBannerID(String bannerID) {
        this.bannerID = bannerID;
    }
}
