package com.csci5308.groupme.course.survey.model;

import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.model.SurveyQuestionList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class SurveyQuestionListTest {

    @Test
    void getSurveyQuestionListTest() {
        SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId("1");
        List<SurveyQuestion> questionList = new ArrayList<>();
        questionList.add(surveyQuestion);
        surveyQuestionList.setSurveyQuestionList(questionList);
        assertEquals(surveyQuestionList.getSurveyQuestionList().size(), 1);
    }

    @Test
    void setSurveyQuestionListTest() {
        SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId("1");
        List<SurveyQuestion> questionList = new ArrayList<>();
        questionList.add(surveyQuestion);
        surveyQuestionList.setSurveyQuestionList(questionList);
        assertEquals(surveyQuestionList.getSurveyQuestionList().size(), 1);
    }
}