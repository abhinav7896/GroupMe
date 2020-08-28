package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;

import java.util.List;
import java.util.Map;

public interface SurveyOperationService {

    public List<Question> showQuestionsOnCreateSurveyPage(String courseCode, String roleName, String userName);

    public int addQuestionToSurvey(String courseCode, SurveyQuestion surveyQuestion) throws Exception;

    public int removeQuestionFromSurvey(String questionId, String courseCode) throws Exception;

    public Map<String, Integer> checkIfSurveyExist(String courseCode);

    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode);

    public List<Candidate> getAllResponsesForSurvey(Integer surveyId);
}
