package com.csci5308.groupme.course.courseadmin.instructor.model;

import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
public class InstructorTest {

    private final String userNameTest = "savani";
    private final String firstNameTest = "Yashesh";
    private final String lastNameTest = "Savani";
    private final String aboutTest = "Instructor";
    private final String emailTest = "yashesh@dal.ca";

    @Test
    public void getUserNameTest() {
        Instructor instructor = new Instructor();
        instructor.setUserName(userNameTest);
        assertEquals(userNameTest, instructor.getUserName());
    }

    @Test
    public void getFirstNameTest() {
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstNameTest);
        assertEquals(firstNameTest, instructor.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        Instructor instructor = new Instructor();
        instructor.setLastName(lastNameTest);
        assertEquals(lastNameTest, instructor.getLastName());
    }

    @Test
    public void getAboutTest() {
        Instructor instructor = new Instructor();
        instructor.setAbout(aboutTest);
        assertEquals(aboutTest, instructor.getAbout());
    }

    @Test
    public void setUserNameTest() {
        Instructor instructor = new Instructor();
        instructor.setUserName(userNameTest);
        assertEquals(userNameTest, instructor.getUserName());
    }

    @Test
    public void setFirstNameTest() {
        Instructor instructor = new Instructor();
        instructor.setFirstName(firstNameTest);
        assertEquals(firstNameTest, instructor.getFirstName());
    }

    @Test
    public void setLastNameTest() {
        Instructor instructor = new Instructor();
        instructor.setLastName(lastNameTest);
        assertEquals(lastNameTest, instructor.getLastName());
    }

    @Test
    public void setAboutTest() {
        Instructor instructor = new Instructor();
        instructor.setAbout(aboutTest);
        assertEquals(aboutTest, instructor.getAbout());
    }

    @Test
    public void getEmailTest() {
        Instructor instructor = new Instructor();
        instructor.setEmail(emailTest);
        assertEquals(emailTest, instructor.getEmail());
    }

    @Test
    public void setEmailTest() {
        Instructor instructor = new Instructor();
        instructor.setEmail(emailTest);
        assertEquals(emailTest, instructor.getEmail());
    }

}
