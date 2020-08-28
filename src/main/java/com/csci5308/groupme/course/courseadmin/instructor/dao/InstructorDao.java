package com.csci5308.groupme.course.courseadmin.instructor.dao;

import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;

public interface InstructorDao {

    public Instructor findByUserName(String userName) throws Exception;

    public int save(Instructor instructor) throws Exception;
}
