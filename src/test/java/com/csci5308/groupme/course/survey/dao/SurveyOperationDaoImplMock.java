package com.csci5308.groupme.course.survey.dao;

import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.survey.model.Candidate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyOperationDaoImplMock implements SurveyOperationDao {

    private final String questionTitleTest = "Python";
    private final Integer questionIdTest = 23;
    private final String questionTest = "What is your proficiency level in Python?";
    private final String questionTypeTest = QuestionTypeConstants.NUMERIC;
    private final String firstNameTest = "Yashesh";
    private final String lastNameTest = "Savani";
    private final String bannerIdTest = "B00838281";
    private final String userNameTest = "ysavani";
    private final String surveyQuestionTest = "{" +
            "\"1\": { " +
            " \"title\": \"default\"," +
            "\"question\": \"default\"," +
            "\"type\": \"Numeric\"," +
            "\"criterion\": \"1\"," +
            "\"weight\": \"5\"," +
            "\"upperBound\": \"none\"," +
            "\"lowerBound\": \"none\"}" +
            "}";
    private final String surveyResponseTest = "{" +
            "\"1\": { " +
            " \"title\": \"default\"," +
            "\"question\": \"default\"," +
            "\"type\": \"Numeric\"," +
            "\"criterion\": \"1\"," +
            "\"weight\": \"5\"," +
            "\"upperBound\": \"none\"," +
            "\"lowerBound\": \"none\"" +
            "\"answer\": \"1\"}" +
            "}";

    @Override
    public List<Question> showQuestionsOnCreateSurveyPage(String userName, String courseCode, String roleName) {

        List<Question> notAddedQuestion = new ArrayList<>();
        notAddedQuestion.add(new Question(questionTitleTest, questionIdTest, questionTest, questionTypeTest));
        return notAddedQuestion;
    }

    @Override
    public String getJsonObjectOfQuestions(String courseCode) {
        return surveyQuestionTest;
    }

    @Override
    public int insertQuestionToSurvey(String courseCode, String jsonString, Integer questionId) {
        return 1;
    }

    @Override
    public int deleteQuestionFromSurvey(String courseCode, String jsonString, Integer questionId) {
        return 1;
    }

    @Override
    public Map<String, Integer> checkIfSurveyExist(String courseCode) {

        Map<String, Integer> conditions = new HashMap<>();
        conditions.put("surveyId", 1);
        conditions.put("isPublished", 1);
        return conditions;
    }

    @Override
    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode) {
        List<Question> addedQuestion = new ArrayList<>();
        addedQuestion.add(new Question(questionTitleTest, questionIdTest, questionTest, questionTypeTest));
        return addedQuestion;
    }

    @Override
    public List<Candidate> getSurveyResponses(Integer surveyId) {
        List<Candidate> candidateList = new ArrayList<>();
        Candidate candidate = new Candidate();
        candidate.setSurveyId(surveyId);
        candidate.setUserName(userNameTest);
        candidate.setStringifiedResponses(surveyResponseTest);
        candidate.setBannerId(bannerIdTest);
        candidate.setFirstName(firstNameTest);
        candidate.setLastName(lastNameTest);
        candidateList.add(candidate);
        return candidateList;
    }
}
