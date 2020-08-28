package com.csci5308.groupme.course.survey.dao;

public interface SurveyCustomiseDao {

    public String getSurveyQuestions(String courseCode);

    public int checkIfSurveyIsPublished(String courseCode);

    public int insertCustomisedQuestionsToSurvey(String courseCode, String newSurveyQuestions);
}
