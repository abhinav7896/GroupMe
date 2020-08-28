package com.csci5308.groupme.course.survey.dao;

public class SurveyPublishDaoImplMock implements SurveyPublishDao {

    @Override
    public int publishSurveyForStudents(String roleName, String courseCode) {
        return 1;
    }
}
