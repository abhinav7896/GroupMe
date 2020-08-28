package com.csci5308.groupme.course.courseadmin.instructor.dao;

import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QuestionsDaoMock implements QuestionsDao {

    @Override
    public int saveNonMCQ(String instructorUserName, Question question) {
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        return questions.size();
    }

    @Override
    public int saveMultipleChoiceQuestion(Question question, List<Option> optionList, String instructorUserName) {
        List<Question> questions = new ArrayList<>();
        questions.add(question);
        return questions.size() + optionList.size();
    }

    @Override
    public List<Question> findAllQuestions(String instructorUserName) {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1);
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setTitle("Python");
        question.setQuestion("How good is your Python?");
        question.setType("Free text");
        questions.add(question);
        return questions;
    }

    @Override
    public int removeQuestion(String instructorUserName, Question question) {
        return 1;
    }

    @Override
    public List<Question> findAllTitles(String instructorUserName) {
        List<Question> questions = new ArrayList<>();
        Question question = new Question();
        question.setQuestionId(1);
        question.setCreatedDate(new Date(System.currentTimeMillis()));
        question.setTitle("Python");
        questions.add(question);
        return questions;
    }

    @Override
    public List<Question> findAllSortedTitlesByTitles(String instructorUserName) {
        List<Question> questions = new ArrayList<>();
        Question questionOne = new Question();
        Question questionTwo = new Question();
        questionOne.setTitle("Python");
        questionTwo.setTitle("Java");
        questions.add(questionOne);
        questions.add(questionTwo);
        return questions;
    }

    @Override
    public List<Question> findAllSortedTitlesByDates(String instructorUserName) {
        List<Question> questions = new ArrayList<>();
        Question questionOne = new Question();
        Question questionTwo = new Question();
        questionOne.setTitle("Python");
        questionOne.setCreatedDate(new Date(System.currentTimeMillis()));
        questionTwo.setTitle("Java");
        questionTwo.setCreatedDate(new Date(System.currentTimeMillis()));
        questions.add(questionOne);
        questions.add(questionTwo);
        return questions;
    }

}
