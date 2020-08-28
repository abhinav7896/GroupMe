package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.TestsConfig;
import constants.Messages;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class SurveyPublishServiceImplTest {

    private SurveyPublishService surveyPublishService;
    private String roleNameTest = Roles.INSTRUCTOR;
    private String courseCodeTest = "csci0010";
    private Logger logger = LoggerFactory.getLogger(SurveyPublishServiceImplTest.class);

    public SurveyPublishServiceImplTest() {
        surveyPublishService = TestsConfig.instance().getSurveyPublishService();
    }

    @Test
    void publishSurveyForStudentsTest() {

        String publisherMessage = surveyPublishService.publishSurveyForStudents(roleNameTest, courseCodeTest);
        String publisherMessageTest = Messages.SURVEY_PUBLISHED + courseCodeTest;
        logger.info(publisherMessage);
        assertEquals(publisherMessageTest, publisherMessage);
    }
}