package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.TestsConfig;
import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.model.SurveyQuestionList;
import constants.Messages;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyCustomiseServiceImplTest {

    private SurveyCustomiseService surveyCustomiseService;
    private String courseCodeTest = "csci0010";
    private Logger logger = LoggerFactory.getLogger(SurveyCustomiseServiceImplTest.class);

    public SurveyCustomiseServiceImplTest() {
        surveyCustomiseService = TestsConfig.instance().getSurveyCustomiseService();
    }

    @Test
    void getSurveyQuestionsTest() {
        List<SurveyQuestion> surveyQuestionList;
        surveyQuestionList = surveyCustomiseService.getSurveyQuestions(courseCodeTest);
        logger.info("Added question in list: {} ", surveyQuestionList);
        assertEquals(surveyQuestionList.size(), 1);
    }


    @Test
    void checkIfSurveyIsPublishedTest() {
        int rowCount = surveyCustomiseService.checkIfSurveyIsPublished(courseCodeTest);
        logger.info("Survey publish status: " + rowCount);
        assertEquals(rowCount, 0);
    }

    @Test
    void saveCustomisedQuestionsToSurveyTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId("1");
        surveyQuestion.setQuestionTitle("Python");
        surveyQuestion.setQuestion("How good are you in Python?");
        surveyQuestion.setQuestionType(QuestionTypeConstants.NUMERIC);
        surveyQuestion.setCriterion("1");
        surveyQuestion.setWeight("1");
        surveyQuestion.setUpperBound(Messages.NONE);
        surveyQuestion.setLowerBound(Messages.NONE);
        List<SurveyQuestion> questionList = new ArrayList<>();
        questionList.add(surveyQuestion);
        SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
        surveyQuestionList.setSurveyQuestionList(questionList);
        int rowCount = surveyCustomiseService.saveCustomisedQuestionsToSurvey(surveyQuestionList, courseCodeTest);
        logger.info("Number of customised survey added to database: " + rowCount);
        assertEquals(rowCount, 1);

    }
}