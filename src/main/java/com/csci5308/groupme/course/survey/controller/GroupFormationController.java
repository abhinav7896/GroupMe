package com.csci5308.groupme.course.survey.controller;

import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.survey.constants.SurveyConstants;
import com.csci5308.groupme.course.survey.model.Candidate;
import com.csci5308.groupme.course.survey.model.Group;
import com.csci5308.groupme.course.survey.model.PrettyResponse;
import com.csci5308.groupme.course.survey.service.GroupFormationService;
import com.csci5308.groupme.course.survey.service.SurveyOperationService;
import constants.Messages;
import errors.EditCodes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/grouping")
public class GroupFormationController {
    private final Logger logger = LoggerFactory.getLogger(GroupFormationController.class);
    private GroupFormationService groupFormationService = SystemConfig.instance().getGroupFormationService();
    private SurveyOperationService surveyOperationService = SystemConfig.instance().getSurveyOperationService();

    @RequestMapping(value = "/configureGroupingStrategy", method = RequestMethod.GET)
    public String configureGroupingStrategy(Model model, @RequestParam("courseCode") String courseCode,
                                            @RequestParam(value = "message", required = false) String message) {
        Integer surveyId = null;
        Integer isPublished = null;
        List<String> groupingStrategies = new ArrayList<String>();
        Map<String, Integer> surveyIdStatus = surveyOperationService.checkIfSurveyExist(courseCode);
        if (surveyIdStatus == null) {
            message = Messages.NO_SURVEY_FOR_COURSE;
        } else {
            surveyId = surveyIdStatus.get("surveyId");
            isPublished = surveyIdStatus.get("isPublished");
            logger.info("Survey exits for course: Survey id: {}, Survey status {}", surveyId, isPublished);
            if (isPublished == SurveyConstants.SURVEY_STATUS_NOT_PUBLISHED) {
                message = Messages.SURVEY_NOT_PUBLISHED;
            } else {
                groupingStrategies = groupFormationService.getAllGroupingStrategies();
                message = Messages.SELECT_GROUPING_FORMULA;
            }
        }
        model.addAttribute("message", message);
        model.addAttribute("groupingStrategies", groupingStrategies);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("isPublished", isPublished);
        model.addAttribute("courseCode", "courseCode");
        return "survey/selectgroupingformula";
    }

    @RequestMapping(value = "/getGroups", method = RequestMethod.GET)
    public String showGroupsFormed(Model model, @RequestParam("algorithm") String algorithm,
                                   @RequestParam("groupSize") Integer groupSize, @RequestParam("surveyId") Integer surveyId) {
        int status = EditCodes.DEFAULT;
        String message = null;
        List<Candidate> candidates = surveyOperationService.getAllResponsesForSurvey(surveyId);
        if (candidates == null || candidates.isEmpty()) {
            message = Messages.NO_RESPONSES;
            model.addAttribute("message", message);
            return "survey/error";
        }
        for (Candidate candidate : candidates) {
            logger.info("User Name : {}", candidate.getUserName());
            logger.info("Responses : {}", candidate.getStringifiedResponses());
            candidate.storeResponsesAsMap(candidate.getStringifiedResponses());
        }
        status = groupFormationService.validate(candidates, groupSize);
        if (status == EditCodes.GROUP_SIZE_IS_ZERO) {
            message = Messages.GROUP_SIZE_IS_ZERO;
            model.addAttribute("message", message);
            return "survey/error";
        } else if (status == EditCodes.GROUP_SIZE_GREATER_THAN_STRENGTH) {
            message = Messages.GROUP_SIZE_GREATER_THAN_STRENGTH;
            model.addAttribute("message", message);
            return "survey/error";
        }
        groupFormationService.configureGroupingStrategy(algorithm);
        List<Group> groups = groupFormationService.formGroups(candidates, groupSize);
        for (Group group : groups) {
            logger.info("Group formed: Group-" + group.getGroupNo());
            for (Candidate candidate : group.getCandidates()) {
                logger.info("candidate id : {} ", candidate.getBannerId());
                for (PrettyResponse prettyResponse : candidate.getPrettyResponses()) {
                    logger.info("Title : {}", prettyResponse.getTitle());
                }
            }
        }
        model.addAttribute("groups", groups);
        model.addAttribute("message", message);
        return "survey/displaygroups";
    }
}
