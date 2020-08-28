package com.csci5308.groupme.course.survey.model;

import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SurveyQuestionTest {

    private String questionIdTest = "1";
    private String questionTitleTest = "Python";
    private String questionTypeTest = QuestionTypeConstants.NUMERIC;
    private String questionTest = "Proficiency Level";
    private String criterionTest = "similar";
    private String weightTest = "1";
    private String upperBoundTest = "8";
    private String lowerBoundTest = "3";

    @Test
    void getQuestionIdTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId(questionIdTest);
        assertEquals(surveyQuestion.getQuestionId(), questionIdTest);
    }

    @Test
    void setQuestionIdTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId(questionIdTest);
        assertEquals(surveyQuestion.getQuestionId(), questionIdTest);
    }

    @Test
    void getQuestionTitleTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionTitle(questionTitleTest);
        assertEquals(surveyQuestion.getQuestionTitle(), questionTitleTest);
    }

    @Test
    void setQuestionTitleTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionTitle(questionTitleTest);
        assertEquals(surveyQuestion.getQuestionTitle(), questionTitleTest);
    }

    @Test
    void getQuestionTypeTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionType(questionTypeTest);
        assertEquals(surveyQuestion.getQuestionType(), questionTypeTest);
    }

    @Test
    void setQuestionTypeTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionType(questionTypeTest);
        assertEquals(surveyQuestion.getQuestionType(), questionTypeTest);
    }

    @Test
    void getQuestionTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestion(questionTest);
        assertEquals(surveyQuestion.getQuestion(), questionTest);
    }

    @Test
    void setQuestionTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestion(questionTest);
        assertEquals(surveyQuestion.getQuestion(), questionTest);
    }

    @Test
    void getCriterionTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setCriterion(criterionTest);
        assertEquals(surveyQuestion.getCriterion(), criterionTest);
    }

    @Test
    void setCriterionTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setCriterion(criterionTest);
        assertEquals(surveyQuestion.getCriterion(), criterionTest);
    }

    @Test
    void getWeightTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setWeight(weightTest);
        assertEquals(surveyQuestion.getWeight(), weightTest);
    }

    @Test
    void setWeightTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setWeight(weightTest);
        assertEquals(surveyQuestion.getWeight(), weightTest);
    }

    @Test
    void getUpperBoundTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setUpperBound(upperBoundTest);
        assertEquals(surveyQuestion.getUpperBound(), upperBoundTest);
    }

    @Test
    void setUpperBoundTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setUpperBound(upperBoundTest);
        assertEquals(surveyQuestion.getUpperBound(), upperBoundTest);
    }

    @Test
    void getLowerBoundTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setLowerBound(lowerBoundTest);
        assertEquals(surveyQuestion.getLowerBound(), lowerBoundTest);
    }

    @Test
    void setLowerBoundTest() {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setLowerBound(lowerBoundTest);
        assertEquals(surveyQuestion.getLowerBound(), lowerBoundTest);
    }
}