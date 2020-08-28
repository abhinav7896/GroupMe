package com.csci5308.groupme.user.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void getResetPasswordPageTest() throws Exception {
		String testResetPasswordLink = "/resetPassword?secretCode=21wyp882e81dgw5&email=abhinav.mandava78@gmail.com";
		this.mockMvc.perform(get(testResetPasswordLink).secure(true)).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("auth/resetPassword"));
	}
	
	@Test
	void getResetPasswordPageNoAccessTest() throws Exception {
		String testResetPasswordLink = "/resetPassword?secretCode=22333&email=abhinav.mandava78@gmail.com";
		this.mockMvc.perform(get(testResetPasswordLink).secure(true)).andDo(print()).andExpect(status().isOk())
				.andExpect(view().name("auth/unauthorized"));
	}
	
	
}
