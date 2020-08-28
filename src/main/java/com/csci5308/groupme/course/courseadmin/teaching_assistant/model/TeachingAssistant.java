package com.csci5308.groupme.course.courseadmin.teaching_assistant.model;

public class TeachingAssistant {

    private String userName;
    private String firstName;
    private String lastName;

    public TeachingAssistant() {
        this.userName = null;
        this.firstName = null;
        this.lastName = null;
    }

    public TeachingAssistant(String userName, String firstName, String lastName) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
