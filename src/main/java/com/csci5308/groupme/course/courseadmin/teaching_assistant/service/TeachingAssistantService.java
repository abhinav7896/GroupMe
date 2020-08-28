package com.csci5308.groupme.course.courseadmin.teaching_assistant.service;

public interface TeachingAssistantService {

    int assignTAToCourse(String emailId, String courseCode) throws Exception;

}
