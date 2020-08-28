package com.csci5308.groupme.course.courseadmin.teaching_assistant.model;

import com.csci5308.groupme.course.courseadmin.teaching_assistant.model.TeachingAssistant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class TeachingAssistantTest {

    private String userNameTest = "ysavani";
    private String firstNameTest = "Yashesh";
    private String lastNameTest = "Savani";

    @Test
    public void defaultConstructorTest() {
        TeachingAssistant TA = new TeachingAssistant();
        assertNull(TA.getUserName());
        assertNull(TA.getFirstName());
        assertNull(TA.getLastName());
    }

    @Test
    public void constructorWithValuesTest() {
        TeachingAssistant TA = new TeachingAssistant(userNameTest, firstNameTest, lastNameTest);
        assertEquals(userNameTest, TA.getUserName());
        assertEquals(firstNameTest, TA.getFirstName());
        assertEquals(lastNameTest, TA.getLastName());
    }

    @Test
    public void getFirstNameTest() {
        TeachingAssistant TA = new TeachingAssistant();
        TA.setFirstName(firstNameTest);
        assertEquals(firstNameTest, TA.getFirstName());
    }

    @Test
    public void getLastNameTest() {
        TeachingAssistant TA = new TeachingAssistant();
        TA.setLastName(lastNameTest);
        assertEquals(lastNameTest, TA.getLastName());
    }

    @Test
    public void setFirstNameTest() {
        TeachingAssistant TA = new TeachingAssistant();
        TA.setFirstName(firstNameTest);
        assertEquals(firstNameTest, TA.getFirstName());
    }

    @Test
    public void setLastNameTest() {
        TeachingAssistant TA = new TeachingAssistant();
        TA.setLastName(lastNameTest);
        assertEquals(lastNameTest, TA.getLastName());
    }

    @Test
    public void getUserNameTest() {
        TeachingAssistant TA = new TeachingAssistant();
        TA.setUserName(userNameTest);
        assertEquals(userNameTest, TA.getUserName());
    }

    @Test
    public void setUserNameTest() {
        TeachingAssistant TA = new TeachingAssistant();
        TA.setUserName(userNameTest);
        assertEquals(userNameTest, TA.getUserName());
    }
}
