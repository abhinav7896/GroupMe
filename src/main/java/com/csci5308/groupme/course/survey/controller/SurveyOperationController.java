package com.csci5308.groupme.course.survey.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.survey.constants.SurveyConstants;
import com.csci5308.groupme.course.survey.model.SurveyQuestion;
import com.csci5308.groupme.course.survey.service.SurveyOperationService;
import constants.Messages;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/survey")
public class SurveyOperationController {

    private final String courseCodeParam = "courseCode";
    private final String roleNameParam = "roleName";
    private final String questionTypeParam = "questionType";
    private final String questionIdParam = "questionId";
    private final String questionParam = "question";
    private final String questionTitleParam = "questionTitle";
    private final Logger logger = LoggerFactory.getLogger(SurveyOperationController.class);
    SurveyOperationService surveyOperationService;

    @RequestMapping(value = "/createSurvey", method = RequestMethod.GET)
    public ModelAndView showCreateSurveyPage(@RequestParam(courseCodeParam) String courseCode,
                                             @RequestParam(roleNameParam) String roleName,
                                             Principal principal) {
        String message;
        List<Question> addedQuestions;
        ModelAndView modelAndView = new ModelAndView();
        String userName = principal.getName();
        surveyOperationService = SystemConfig.instance().getSurveyOperationService();
        Map<String, Integer> conditions = surveyOperationService.checkIfSurveyExist(courseCode);
        if (conditions.get("isPublished") != SurveyConstants.SURVEY_STATUS_PUBLISHED) {
            logger.info("Survey for course: " + courseCode + " is not Published");
            if (conditions.get("surveyId") != SurveyConstants.DEFAULT_SURVEY_ID) {
                logger.info("Survey Exists for course: " + courseCode);
                addedQuestions = surveyOperationService.getAlreadyAddedSurveyQuestions(userName, roleName, courseCode);
            } else {
                addedQuestions = new ArrayList<>();
            }
            List<Question> notAddedQuestions = surveyOperationService.showQuestionsOnCreateSurveyPage(courseCode, roleName, userName);
            if (null != notAddedQuestions) {
                modelAndView.addObject("notAddedQuestions", notAddedQuestions);
                modelAndView.addObject("addedQuestions", addedQuestions);
                message = Messages.QUESTIONS_FETCHED;
                modelAndView.addObject("message", message);
            } else {
                notAddedQuestions = new ArrayList<>();
                modelAndView.addObject("notAddedQuestions", notAddedQuestions);
                modelAndView.addObject("addedQuestions", addedQuestions);
                message = Messages.QUESTIONS_NOT_FETCHED;
                modelAndView.addObject("message", message);
            }
            modelAndView.addObject("publisherMessage", Messages.SURVEY_NOT_PUBLISHED);
        } else {
            logger.info("Survey for course: " + courseCode + " is Published");
            modelAndView.addObject("publisherMessage", Messages.SURVEY_ALREADY_PUBLISHED);
        }
        modelAndView.addObject("courseCode", courseCode);
        modelAndView.addObject("roleName", roleName);
        modelAndView.setViewName("survey/createsurvey");
        return modelAndView;
    }

    @RequestMapping(value = "/addQuestionToSurvey", method = RequestMethod.POST)
    public ModelAndView addQuestionToSurvey(@RequestParam(value = questionIdParam) String questionId,
                                            @RequestParam(value = questionParam) String question,
                                            @RequestParam(value = questionTitleParam) String questionTitle,
                                            @RequestParam(value = courseCodeParam) String courseCode,
                                            @RequestParam(value = roleNameParam) String roleName,
                                            @RequestParam(value = questionTypeParam) String questionType) throws Exception {
        surveyOperationService = SystemConfig.instance().getSurveyOperationService();
        ModelAndView modelAndView = new ModelAndView();
        SurveyQuestion surveyQuestion = new SurveyQuestion();
        surveyQuestion.setQuestionId(questionId);
        surveyQuestion.setQuestion(question);
        surveyQuestion.setQuestionTitle(questionTitle);
        surveyQuestion.setQuestionType(questionType);
        surveyQuestion.setCriterion(Messages.NONE);
        surveyQuestion.setUpperBound(Messages.NONE);
        surveyQuestion.setLowerBound(Messages.NONE);
        surveyQuestion.setWeight(Messages.NONE);
        logger.debug("surveyQuestion object created for QuestionId: " + questionId + "and Question: " + question);
        int rowCount = surveyOperationService.addQuestionToSurvey(courseCode, surveyQuestion);
        logger.info("Number of questions added to survey: " + rowCount);
        modelAndView.setViewName("redirect:/survey/createSurvey");
        modelAndView.addObject("courseCode", courseCode);
        modelAndView.addObject("roleName", roleName);
        return modelAndView;
    }

    @RequestMapping(value = "/removeQuestionFromSurvey", method = RequestMethod.POST)
    public ModelAndView removeQuestionFromSurvey(@RequestParam(value = questionIdParam) String questionId,
                                                 @RequestParam(value = courseCodeParam) String courseCode,
                                                 @RequestParam(value = roleNameParam) String roleName) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        surveyOperationService = SystemConfig.instance().getSurveyOperationService();
        int rowCount = surveyOperationService.removeQuestionFromSurvey(questionId, courseCode);
        logger.info("Number of questions removed from survey: " + rowCount);
        modelAndView.setViewName("redirect:/survey/createSurvey");
        modelAndView.addObject("courseCode", courseCode);
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
