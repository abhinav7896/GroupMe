package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.TestsConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import constants.Messages;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class SurveyOperationServiceImplTest {

    private SurveyOperationService surveyOperationService;
    private String userNameTest = "ysavani";
    private String roleNameTest = Roles.INSTRUCTOR;
    private String courseCodeTest = "csci0010";
    private String questionTest = "Level in Python";
    private String questionTitleTest = "Python";
    private String questionTypeTest = "Numeric";
    private String questionIdTest = "2";
    private Logger logger = LoggerFactory.getLogger(SurveyCustomiseServiceImplTest.class);

    public SurveyOperationServiceImplTest() {
        surveyOperationService = TestsConfig.instance().getSurveyOperationService();
    }

    @Test
    void showQuestionsOnCreateSurveyPageTest() {
        List<Question> notAddedQuestion = surveyOperationService.showQuestionsOnCreateSurveyPage(courseCodeTest, roleNameTest, userNameTest);
        logger.info("List of question not added to survey: {} ", notAddedQuestion);
        assertEquals(notAddedQuestion.size(), 1);
    }

    @Test
    void addQuestionToSurveyTest() throws Exception {
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId(questionIdTest);
        surveyQuestion.setQuestion(questionTest);
        surveyQuestion.setQuestionTitle(questionTitleTest);
        surveyQuestion.setQuestionType(questionTypeTest);
        surveyQuestion.setCriterion(Messages.NONE);
        surveyQuestion.setUpperBound(Messages.NONE);
        surveyQuestion.setLowerBound(Messages.NONE);
        surveyQuestion.setWeight(Messages.NONE);
        int rowCount = surveyOperationService.addQuestionToSurvey(courseCodeTest, surveyQuestion);
        logger.info("Number of queestion added to Survey: {} ", rowCount);
        assertEquals(rowCount, 1);
    }

    @Test
    void getAlreadyAddedSurveyQuestionsTest() {
        List<Question> addedQuestion = surveyOperationService.getAlreadyAddedSurveyQuestions(userNameTest, roleNameTest, courseCodeTest);
        logger.info("Questions already in survey: {} ", addedQuestion);
        assertEquals(addedQuestion.size(), 1);
    }

    @Test
    void checkIfSurveyExistTest() {
        Map<String, Integer> conditions = surveyOperationService.checkIfSurveyExist(courseCodeTest);
        assertEquals(conditions.get("surveyId"), 1);
        assertEquals(conditions.get("isPublished"), 1);
    }

    @Test
    void removeQuestionFromSurveyTest() throws Exception {
        int rowCount = surveyOperationService.removeQuestionFromSurvey(questionIdTest, courseCodeTest);
        logger.info("Questions removed from survey with question id: {} ", questionIdTest);
        assertEquals(rowCount, 1);
    }
}
