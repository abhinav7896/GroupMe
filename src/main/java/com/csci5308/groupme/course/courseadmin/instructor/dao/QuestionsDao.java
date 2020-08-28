package com.csci5308.groupme.course.courseadmin.instructor.dao;

import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;

import java.util.List;

public interface QuestionsDao {

    public int saveNonMCQ(String instructorUserName, Question question);

    public int saveMultipleChoiceQuestion(Question question, List<Option> optionList, String instructorUserName);

    public List<Question> findAllTitles(String instructorUserName);

    public int removeQuestion(String instructorUserName, Question question);

    public List<Question> findAllSortedTitlesByTitles(String instructorUserName);

    public List<Question> findAllSortedTitlesByDates(String instructorUserName);

    public List<Question> findAllQuestions(String instructorUserName);

}
