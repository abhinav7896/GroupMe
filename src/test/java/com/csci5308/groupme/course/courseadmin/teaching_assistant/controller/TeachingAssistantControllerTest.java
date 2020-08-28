package com.csci5308.groupme.course.courseadmin.teaching_assistant.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class TeachingAssistantControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void showStudentHomePageTest() throws Exception {
        this.mockMvc.perform(get("/tahomepage"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("ta/tahomepage"));
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void getCoursesByUserNameAndRoleTest() throws Exception {
        this.mockMvc.perform(get("/TAcoursepage")
                .param("roleName", Roles.TA))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("CourseAdmin"));
    }
}