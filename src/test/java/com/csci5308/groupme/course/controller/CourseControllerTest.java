package com.csci5308.groupme.course.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "savani", password = "admin19", authorities = {Roles.TA})
    public void showOperationsOnCourseTest() throws Exception {

        this.mockMvc.perform(get("/operationoncourse").param("courseCode", "csci0010")
                .param("courseName", "web"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attributeExists("courseCode", "courseName"));

    }

    @Test
    @WithMockUser(username = "savani", password = "admin19", authorities = {Roles.TA})
    public void getCoursePageTest() throws Exception {

        this.mockMvc.perform(get("/coursepage").param("courseCode", "CSCI5308")
                .param("courseName", "Adv Topics in Software Development"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(model().attributeExists("courseCode", "courseName"));

    }

}
