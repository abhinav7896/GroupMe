package com.csci5308.groupme.course.courseadmin.instructor.service;

import com.csci5308.groupme.course.courseadmin.instructor.model.ListOfOptions;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;

import java.util.List;

public interface QuestionManagerService {

    public int createQuestion(String instructorUserName, Question question, ListOfOptions options);

    public List<Question> getAllTitles(String instructorUserName);

    public int deleteQuestion(String instructorUserName, Question question);

    public List<Question> getAllSortedTitlesByTitles(String instructorUserName);

    public List<Question> getAllSortedTitlesByDates(String instructorUserName);

    public List<Question> getAllQuestions(String instructorUserName);
}
