package com.csci5308.groupme.course.courseadmin.instructor.controller;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void courseAdminPageTest() throws Exception {

        this.mockMvc.perform(get("/instructor/courseAdminPage").param("roleName", Roles.TA))
                .andDo(print()).andExpect(status().is(403));
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void insertByTAEmailIdTest() throws Exception {
        this.mockMvc.perform(post("/courseoperation").param("courseCode", "csci0010")
                .param("email", "ys@gmail.com")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "iuser", password = "password", authorities = {Roles.INSTRUCTOR})
    void instructorHomePageTest() throws Exception {
        this.mockMvc.perform(get("/instructorhomepage"))
                .andDo(print()).andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "ysavani", password = "admin19", authorities = {Roles.STUDENT, Roles.TA})
    void courseAdminTest() throws Exception {
        this.mockMvc.perform(get("/courseAdmin/course").param("courseCode", "csci0010")
                .param("courseName", "web")
                .param("courseCrn", "8920")).andDo(print()).andExpect(status().isOk());
    }
}
