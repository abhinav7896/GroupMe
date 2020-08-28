package com.csci5308.groupme.course.model;

import org.springframework.stereotype.Component;

@Component
public class Course {

    private String courseCode;
    private String courseName;
    private Integer courseCrn;

    public Course() {
        this.courseCode = null;
        this.courseName = null;
        this.courseCrn = 0;
    }

    public Course(String courseCode, String courseName, Integer courseCrn) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCrn = courseCrn;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getCourseCrn() {
        return courseCrn;
    }

    public void setCourseCrn(Integer courseCrn) {
        this.courseCrn = courseCrn;
    }

}
