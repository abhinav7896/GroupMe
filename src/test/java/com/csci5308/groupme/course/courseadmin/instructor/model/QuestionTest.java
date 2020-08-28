package com.csci5308.groupme.course.courseadmin.instructor.model;

import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(SpringExtension.class)
public class QuestionTest {

    private Integer questionIdTest = 1;
    private String titleTest = "Python";
    private String questionTest = "What is your proficiency in Python?";
    private String typeTest = "Multiple choice – choose one ";
    private Date createdDateTest = new Date(System.currentTimeMillis());

    @Test
    public void defaultConstructorTest() {
        Question question = new Question();
        assertEquals(0, question.getQuestionId());
        assertNull(question.getQuestion());
        assertNull(question.getTitle());
        assertNull(question.getType());
        assertNull(question.getCreatedDate());
    }

    @Test
    public void constructorWithParameterQuestionTitleTest() {
        Question question = new Question(titleTest);
        assertEquals(titleTest, question.getTitle());
    }

    @Test
    public void constructorWithParameterQuestionIdTitleDateTest() {
        Question question = new Question(titleTest, questionIdTest, createdDateTest);
        assertEquals(titleTest, question.getTitle());
        assertEquals(questionIdTest, question.getQuestionId());
        assertEquals(createdDateTest, question.getCreatedDate());
    }

    @Test
    public void constructorWithAllParameters() {
        Question question = new Question(titleTest, questionIdTest, createdDateTest, questionTest, typeTest);
        assertEquals(titleTest, question.getTitle());
        assertEquals(questionIdTest, question.getQuestionId());
        assertEquals(createdDateTest, question.getCreatedDate());
        assertEquals(questionTest, question.getQuestion());
        assertEquals(typeTest, question.getType());
    }

    @Test
    public void getQuestionIdTest() {
        Question question = new Question();
        question.setQuestionId(questionIdTest);
        assertEquals(questionIdTest, question.getQuestionId());
    }

    @Test
    public void setQuestionIdTest() {
        Question question = new Question();
        question.setQuestionId(questionIdTest);
        assertEquals(questionIdTest, question.getQuestionId());
    }

    @Test
    public void getTitleTest() {
        Question question = new Question();
        question.setTitle(titleTest);
        assertEquals(titleTest, question.getTitle());
    }

    @Test
    public void setTitleTest() {
        Question question = new Question();
        question.setTitle(titleTest);
        assertEquals(titleTest, question.getTitle());
    }

    @Test
    public void getQuestionTest() {
        Question question = new Question();
        question.setQuestion(questionTest);
        assertEquals(questionTest, question.getQuestion());
    }

    @Test
    public void setQuestionTest() {
        Question question = new Question();
        question.setQuestion(questionTest);
        assertEquals(questionTest, question.getQuestion());
    }

    @Test
    public void getTypeTest() {
        Question question = new Question();
        question.setType(typeTest);
        assertEquals(typeTest, question.getType());
    }

    @Test
    public void setTypeTest() {
        Question question = new Question();
        question.setType(typeTest);
        assertEquals(typeTest, question.getType());
    }

    @Test
    public void getCreatedDateTest() {
        Question question = new Question();
        question.setCreatedDate(createdDateTest);
        assertEquals(createdDateTest, question.getCreatedDate());
    }

    @Test
    public void setCreatedDateTest() {
        Question question = new Question();
        question.setCreatedDate(createdDateTest);
        assertEquals(createdDateTest, question.getCreatedDate());
    }
}
