package com.csci5308.groupme.course.survey.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.survey.constants.SurveyConstants;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.model.SurveyQuestionList;
import com.csci5308.groupme.course.survey.service.SurveyCustomiseService;
import constants.Messages;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/customiseSurvey")
public class SurveyCustomiseController {

    private final String courseCodeParam = "courseCode";
    private final String roleNameParam = "roleName";
    private final String surveyQuestionsModelAttribute = "surveyQuestions";
    private final Logger logger = LoggerFactory.getLogger(SurveyCustomiseController.class);
    SurveyCustomiseService surveyCustomiseService;

    @RequestMapping(value = "/getSurveyQuestion", method = RequestMethod.GET)
    public ModelAndView getSurveyQuestionsToCustomise(@RequestParam(courseCodeParam) String courseCode,
                                                      @RequestParam(roleNameParam) String roleName) {

        SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
        ModelAndView modelAndView = new ModelAndView();
        surveyCustomiseService = SystemConfig.instance().getSurveyCustomiseService();
        logger.info("Called method to check if survey is published");
        Integer surveyPublishStatus = surveyCustomiseService.checkIfSurveyIsPublished(courseCode);
        if (surveyPublishStatus.equals(SurveyConstants.SURVEY_STATUS_NOT_PUBLISHED)) {
            logger.info("Survey for course: " + courseCode + " is not Published");
            modelAndView.addObject("publisherMessage", Messages.SURVEY_NOT_PUBLISHED);
            List<SurveyQuestion> surveyQuestion = surveyCustomiseService.getSurveyQuestions(courseCode);
            logger.info("Survey question list returned : " + surveyQuestion);
            if (surveyQuestion.size() > SurveyConstants.DEFAULT_LIST_SIZE) {
                surveyQuestionList.setSurveyQuestionList(surveyQuestion);
                modelAndView.addObject(surveyQuestionsModelAttribute, surveyQuestionList);
                modelAndView.addObject("arraySize", 1);
            } else {
                modelAndView.addObject("arraySize", 0);
                modelAndView.addObject("publisherMessage", Messages.CANNOT_CUSTOMISE_SURVEY + courseCode);
            }
        } else {
            modelAndView.addObject("publisherMessage", Messages.SURVEY_ALREADY_PUBLISHED);
        }
        modelAndView.setViewName("survey/customisesurvey");
        modelAndView.addObject(roleNameParam, roleName);
        modelAndView.addObject(courseCodeParam, courseCode);
        modelAndView.addObject("surveyPublish", surveyPublishStatus);
        return modelAndView;
    }

    @RequestMapping(value = "/saveCustomisedSurvey", method = RequestMethod.POST)
    public ModelAndView saveCustomisedSurvey(@ModelAttribute(surveyQuestionsModelAttribute) SurveyQuestionList surveyQuestionList,
                                             @RequestParam(roleNameParam) String roleName,
                                             @RequestParam(courseCodeParam) String courseCode) {

        surveyCustomiseService = SystemConfig.instance().getSurveyCustomiseService();
        ModelAndView modelAndView = new ModelAndView();
        logger.info(String.valueOf(surveyQuestionList.getSurveyQuestionList()));
        Integer rowCount = surveyCustomiseService.saveCustomisedQuestionsToSurvey(surveyQuestionList, courseCode);
        logger.info("{} customised survey saved", rowCount);
        if (roleName.equals(Roles.TA)) {
            modelAndView.setViewName("redirect:/TAcoursepage");
        } else {
            modelAndView.setViewName("redirect:/instructor/courseAdminPage");
        }
        modelAndView.addObject("roleName", roleName);
        return modelAndView;
    }

    @RequestMapping(value = "/redirectToCourseAdmin", method = RequestMethod.POST)
    public ModelAndView redirectToCourseAdmin(@RequestParam(value = roleNameParam) String roleName) {
        ModelAndView modelAndView = new ModelAndView();
        if (roleName.equals(Roles.TA)) {
            modelAndView.setViewName("redirect:/TAcoursepage");
        } else {
            modelAndView.setViewName("redirect:/instructor/courseAdminPage");
        }
        modelAndView.addObject("roleName", roleName);
        return modelAndView;
    }

}
