package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.course.survey.dao.SurveyCustomiseDao;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.model.SurveyQuestionList;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
public class SurveyCustomiseServiceImpl implements SurveyCustomiseService {

    private SurveyCustomiseDao surveyCustomiseDao;
    private Logger logger = LoggerFactory.getLogger(SurveyCustomiseServiceImpl.class);

    public SurveyCustomiseServiceImpl(SurveyCustomiseDao surveyCustomiseDao) {
        this.surveyCustomiseDao = surveyCustomiseDao;
    }

    @Override
    public List<SurveyQuestion> getSurveyQuestions(String courseCode) {

        Map<String, Map<String, String>> dbSurveyForm;
        Map<String, String> parameters;
        SurveyQuestion surveyQuestion;
        String questionId;
        ObjectMapper objectMapper = new ObjectMapper();
        List<SurveyQuestion> surveyQuestionsList = new ArrayList<>();
        logger.info("Called Dao method in SurveyCustomiseDao to get survey Questions");
        String retrievedSurveyQuestions = surveyCustomiseDao.getSurveyQuestions(courseCode);
        try {
            dbSurveyForm = objectMapper.readValue(retrievedSurveyQuestions, Map.class);
            for (Map.Entry<String, Map<String, String>> entry : dbSurveyForm.entrySet()) {
                parameters = entry.getValue();
                questionId = entry.getKey();
                surveyQuestion = new SurveyQuestion();
                surveyQuestion.setQuestionId(questionId);
                surveyQuestion.setQuestion(parameters.get("question"));
                surveyQuestion.setQuestionTitle(parameters.get("title"));
                surveyQuestion.setQuestionType(parameters.get("type"));
                surveyQuestion.setUpperBound(parameters.get("upperBound"));
                surveyQuestion.setLowerBound(parameters.get("lowerBound"));
                surveyQuestion.setCriterion(parameters.get("criterion"));
                surveyQuestion.setWeight(parameters.get("weight"));
                surveyQuestionsList.add(surveyQuestion);
                logger.info(questionId);
                logger.info(String.valueOf(parameters));
                logger.info("Question added to Questions List: " + questionId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Fetching JSON");
        }
        return surveyQuestionsList;
    }

    @Override
    public int checkIfSurveyIsPublished(String courseCode) {
        Integer surveyIsPublished = surveyCustomiseDao.checkIfSurveyIsPublished(courseCode);
        return surveyIsPublished;
    }

    @Override
    public int saveCustomisedQuestionsToSurvey(SurveyQuestionList surveyQuestionList, String courseCode) {

        Map<String, Map<String, String>> dbSurveyForm = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        Integer rowCount = 0;
        List<SurveyQuestion> questionsList = surveyQuestionList.getSurveyQuestionList();
        for (SurveyQuestion surveyQuestion : questionsList) {
            Map<String, String> parameters = new HashMap<>();
            String questionId = surveyQuestion.getQuestionId();
            parameters.put("title", surveyQuestion.getQuestionTitle());
            parameters.put("question", surveyQuestion.getQuestion());
            parameters.put("type", surveyQuestion.getQuestionType());
            parameters.put("criterion", surveyQuestion.getCriterion());
            parameters.put("weight", surveyQuestion.getWeight());
            parameters.put("upperBound", surveyQuestion.getUpperBound());
            parameters.put("lowerBound", surveyQuestion.getLowerBound());
            dbSurveyForm.put(questionId, parameters);
        }
        try {
            String newSurveyQuestions = objectMapper.writeValueAsString(dbSurveyForm);
            logger.info("Questions in survey to insert: " + newSurveyQuestions);
            rowCount = surveyCustomiseDao.insertCustomisedQuestionsToSurvey(courseCode, newSurveyQuestions);
            logger.info("Customised Survey added to database for Course code:" + courseCode);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Error in Writing JSON of Questions");
        }
        return rowCount;
    }
}

