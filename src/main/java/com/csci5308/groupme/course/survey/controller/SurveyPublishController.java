package com.csci5308.groupme.course.survey.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.model.SurveyQuestionList;
import com.csci5308.groupme.course.survey.service.SurveyCustomiseService;
import com.csci5308.groupme.course.survey.service.SurveyPublishService;
import constants.Messages;
import constants.Roles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SurveyPublishController {

    private final String courseCodeParam = "courseCode";
    private final String roleNameParam = "roleName";
    private final String surveyQuestionsModelAttribute = "surveyQuestions";
    SurveyPublishService surveyPublishService;
    SurveyCustomiseService surveyCustomiseService;

    @RequestMapping(value = "/publishSurvey", method = RequestMethod.GET)
    public ModelAndView publishSurvey(@RequestParam(roleNameParam) String roleName, @RequestParam(courseCodeParam) String courseCode) {

        SurveyQuestionList surveyQuestionList = new SurveyQuestionList();
        surveyPublishService = SystemConfig.instance().getSurveyPublishService();
        surveyCustomiseService = SystemConfig.instance().getSurveyCustomiseService();
        ModelAndView modelAndView = new ModelAndView();
        List<SurveyQuestion> surveyQuestion = surveyCustomiseService.getSurveyQuestions(courseCode);
        surveyQuestionList.setSurveyQuestionList(surveyQuestion);
        if (roleName.equals(Roles.INSTRUCTOR)) {
            String message = surveyPublishService.publishSurveyForStudents(roleName, courseCode);
            modelAndView.addObject("publisherMessage", message);
        } else {
            modelAndView.addObject("publisherMessage", Messages.TA_CANNOT_PUBLISH_SURVEY);
        }
        modelAndView.addObject(surveyQuestionsModelAttribute, surveyQuestionList);
        modelAndView.addObject("roleName", roleName);
        modelAndView.setViewName("survey/publishsurvey");
        return modelAndView;
    }

    @RequestMapping(value = "/exitPublishSurveyPage", method = RequestMethod.POST)
    public ModelAndView exitPublishSurveyPage(@RequestParam(roleNameParam) String roleName) {

        ModelAndView modelAndView = new ModelAndView();
        if (roleName.equals(Roles.TA)) {
            modelAndView.setViewName("redirect:/TAcoursepage");
        } else {
            modelAndView.setViewName("redirect:/instructor/courseAdminPage");
        }
        modelAndView.addObject("roleName", roleName);
        return modelAndView;
    }

    @RequestMapping(value = "/saveSurvey", method = RequestMethod.POST)
    public ModelAndView saveSurveyAndRedirectToCourseAdmin(@RequestParam(value = roleNameParam) String roleName) {
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
