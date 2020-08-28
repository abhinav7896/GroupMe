package com.csci5308.groupme.course.survey.service;

import com.csci5308.groupme.course.survey.constants.SurveyConstants;
import com.csci5308.groupme.course.survey.dao.SurveyPublishDao;
import constants.Messages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SurveyPublishServiceImpl implements SurveyPublishService {

    private SurveyPublishDao surveyPublishDao;
    private Logger logger = LoggerFactory.getLogger(SurveyPublishServiceImpl.class);

    public SurveyPublishServiceImpl(SurveyPublishDao surveyPublishDao) {
        this.surveyPublishDao = surveyPublishDao;
    }

    @Override
    public String publishSurveyForStudents(String roleName, String courseCode) {

        String publisherMessage;
        int surveyPublishStatus = surveyPublishDao.publishSurveyForStudents(roleName, courseCode);
        if (surveyPublishStatus == SurveyConstants.SURVEY_STATUS_PUBLISHED) {
            publisherMessage = Messages.SURVEY_PUBLISHED + courseCode;
            logger.info(publisherMessage);
        } else {
            publisherMessage = Messages.CANNOT_PUBLISH_SURVEY + courseCode;
            logger.info(publisherMessage);
        }
        return publisherMessage;
    }
}
