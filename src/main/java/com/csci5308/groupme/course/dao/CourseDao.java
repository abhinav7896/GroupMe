package com.csci5308.groupme.course.dao;

import com.csci5308.groupme.course.model.Course;

public interface CourseDao {

    public int save(Course course) throws Exception;

    public int remove(String courseCode) throws Exception;

}
