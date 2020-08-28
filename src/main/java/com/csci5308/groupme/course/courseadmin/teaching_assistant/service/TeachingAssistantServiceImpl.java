package com.csci5308.groupme.course.courseadmin.teaching_assistant.service;

import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TeachingAssistantServiceImpl implements TeachingAssistantService {

    private Logger logger = LoggerFactory.getLogger(TeachingAssistantServiceImpl.class);

    private TeachingAssistantDao teachingAssistantDao;

    public TeachingAssistantServiceImpl(TeachingAssistantDao teachingAssistantDao) {
        this.teachingAssistantDao = teachingAssistantDao;
    }

    @Override
    public int assignTAToCourse(String emailId, String courseCode) throws Exception {
        int assignmentConfirmation = 0;
        try {
            assignmentConfirmation = teachingAssistantDao.createTAForCourse(emailId, courseCode);
            logger.info("TA with email: " + emailId + " assigned to course: " + courseCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignmentConfirmation;
    }
}
