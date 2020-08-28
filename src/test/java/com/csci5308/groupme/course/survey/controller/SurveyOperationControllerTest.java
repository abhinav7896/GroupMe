package com.csci5308.groupme.course.survey.controller;

import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import constants.Roles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class SurveyOperationControllerTest {

    private final String courseCodeParam = "courseCode";
    private final String roleNameParam = "roleName";
    private final String questionTypeParam = "questionType";
    private final String questionIdParam = "questionId";
    private final String questionParam = "question";
    private final String questionTitleParam = "questionTitle";
    private final String courseCode = "csci0010";
    private final String roleName = Roles.TA;
    private final String questionType = QuestionTypeConstants.NUMERIC;
    private final String questionId = "1";
    private final String question = "How?";
    private final String questionTitle = "Python";

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void addQuestionToSurveyTest() throws Exception {
        this.mockMvc.perform(post("/survey/addQuestionToSurvey")
                .param(questionIdParam, questionId)
                .param(questionParam, question)
                .param(questionTitleParam, questionTitle)
                .param(courseCodeParam, courseCode)
                .param(roleNameParam, roleName)
                .param(questionTypeParam, questionType)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/survey/createSurvey"));
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void removeQuestionFromSurveyTest() throws Exception {
        this.mockMvc.perform(post("/survey/removeQuestionFromSurvey")
                .param(questionIdParam, questionId)
                .param(courseCodeParam, courseCode)
                .param(roleNameParam, roleName)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/survey/createSurvey"));
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void saveSurveyAndRedirectToCourseAdminTest() throws Exception {
        this.mockMvc.perform(post("/survey/saveSurvey")
                .param(roleNameParam, roleName)).andDo(print()).andExpect(status().is(302))
                .andExpect(view().name("redirect:/TAcoursepage"));
    }
}