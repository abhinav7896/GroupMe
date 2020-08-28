package com.csci5308.groupme.course.survey.dao;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.survey.model.Candidate;

import java.util.List;
import java.util.Map;

public interface SurveyOperationDao {

    public List<Question> showQuestionsOnCreateSurveyPage(String userName, String courseCode, String roleName);

    public String getJsonObjectOfQuestions(String courseCode);

    public int insertQuestionToSurvey(String courseCode, String jsonString, Integer questionId);

    public int deleteQuestionFromSurvey(String courseCode, String jsonString, Integer questionId);

    public Map<String, Integer> checkIfSurveyExist(String courseCode);

    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode);

    List<Candidate> getSurveyResponses(Integer surveyId);
}
