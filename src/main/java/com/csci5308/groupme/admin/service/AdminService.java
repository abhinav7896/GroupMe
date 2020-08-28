package com.csci5308.groupme.admin.service;

public interface AdminService {

    public int assignInstructorToCourse(String emailId, String courseCode) throws Exception;

    public int makeUserAsInstructor(String emailId) throws Exception;

}
