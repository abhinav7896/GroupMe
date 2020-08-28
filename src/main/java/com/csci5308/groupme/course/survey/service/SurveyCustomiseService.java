package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.model.SurveyQuestionList;

import java.util.List;

public interface SurveyCustomiseService {

    public List<SurveyQuestion> getSurveyQuestions(String courseCode);

    public int checkIfSurveyIsPublished(String courseCode);

    int saveCustomisedQuestionsToSurvey(SurveyQuestionList surveyQuestionList, String courseCode);
}
