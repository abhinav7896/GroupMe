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

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class SurveyPublishControllerTest {

    private final String courseCodeParam = "courseCode";
    private final String roleNameParam = "roleName";
    private final String roleName = Roles.INSTRUCTOR;
    private final String courseCode = "csci0010";

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void publishSurveyTest() throws Exception {
        this.mockMvc.perform(get("/publishSurvey")
                .param(roleNameParam, roleName)
                .param(courseCodeParam, courseCode)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("survey/publishsurvey"));
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void exitPublishSurveyPageTest() throws Exception {
        this.mockMvc.perform(post("/exitPublishSurveyPage")
                .param(roleNameParam, roleName)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/instructor/courseAdminPage"));
    }
}