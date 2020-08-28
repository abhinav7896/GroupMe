package com.csci5308.groupme.course.survey.controller;

import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class SurveyCustomiseControllerTest {

    private final String roleNameParam = "roleName";
    private final String courseCodeParam = "courseCode";
    private final String courseCode = "csci0010";
    private final String roleName = Roles.TA;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.TA, Roles.STUDENT})
    void getSurveyQuestionsToCustomiseTest() throws Exception {
        this.mockMvc.perform(get("/customiseSurvey/getSurveyQuestion")
                .param(roleNameParam, roleName)
                .param(courseCodeParam, courseCode)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("survey/customisesurvey"));

    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.TA, Roles.STUDENT})
    void redirectToCourseAdminTest() throws Exception {
        this.mockMvc.perform(post("/customiseSurvey/redirectToCourseAdmin")
                .param(roleNameParam, roleName)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/TAcoursepage"));
    }
}