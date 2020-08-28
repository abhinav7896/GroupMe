package com.csci5308.groupme.course.courseadmin.teaching_assistant.dao;

import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;
import errors.EditCodes;

public class TeachingAssistantDaoMock implements TeachingAssistantDao {

    @Override
    public int createTAForCourse(String emailId, String courseCode) throws Exception {
        String emailMock = "testta@gmail.com";
        String courseCodeMock = "TSCI0000";
        if (emailId.equals(emailMock) && courseCode.equals(courseCodeMock))
            return EditCodes.SUCCESS;
        else
            return EditCodes.EMAIL_DOES_NOT_EXIST;
    }

}
