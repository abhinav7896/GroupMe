package com.csci5308.groupme.course.courseadmin.instructor.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;

public interface InstructorService {

    public Instructor getByUserName(String userName) throws Exception;

    public Instructor getByEmail(String email) throws Exception;

    public int createInstructor(Instructor instructor) throws Exception;

}
