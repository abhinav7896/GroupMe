package com.csci5308.groupme.course.service;

import com.csci5308.groupme.course.model.Course;

public interface CourseService {

    public int createCourse(Course course) throws Exception;

    public int delete(String courseCode) throws Exception;

}
	