package com.csci5308.groupme.admin.controller;

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
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "admin_test", password = "admin19", authorities = {Roles.ADMIN})
    void getAdminPageTest() throws Exception {
        this.mockMvc.perform(get("/admin/manageCourses").secure(true)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("admin/managecourses"));
    }

    @Test
    @WithMockUser(username = "admin_test", password = "admin19", authorities = {Roles.ADMIN})
    void getAddCoursePageTest() throws Exception {
        this.mockMvc.perform(get("/admin/addCourse").secure(true)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("admin/addcourse"));
    }

    @Test
    @WithMockUser(username = "admin_test", password = "admin19", authorities = {Roles.ADMIN})
    void getAddCourseStatusPageTest() throws Exception {
        this.mockMvc.perform(post("/admin/addCourse").secure(true)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("admin/addcourse"));
    }

    @Test
    @WithMockUser(username = "admin_test", password = "admin19", authorities = {Roles.ADMIN})
    void getDeleteCourseStatusPageTest() throws Exception {
        this.mockMvc.perform(post("/admin/deleteCourse").secure(true)).andDo(print()).andExpect(status().isOk())
                .andExpect(view().name("admin/deletecourse"));
    }

}
