package com.csci5308.groupme.course.courseadmin.instructor.service;

import ch.qos.logback.classic.Logger;
import com.csci5308.groupme.course.courseadmin.instructor.constants.QuestionTypeConstants;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDao;
import com.csci5308.groupme.course.courseadmin.instructor.model.ListOfOptions;
import com.csci5308.groupme.course.courseadmin.instructor.model.Option;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;

public class QuestionManagerServiceImpl implements QuestionManagerService {

    private final Logger logger = (Logger) LoggerFactory.getLogger(QuestionManagerServiceImpl.class);

    private final QuestionsDao questionsDao;

    public QuestionManagerServiceImpl(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    @Override
    public int createQuestion(String instructorUserName, Question question, ListOfOptions options) {
        int status = 0;
        long millis = System.currentTimeMillis();
        Date createdDate = new Date(millis);
        try {
            if (question.getType().equals(QuestionTypeConstants.NUMERIC) || question.getType().equals(QuestionTypeConstants.FREE_TEXT)) {
                question.setCreatedDate(createdDate);
                status = questionsDao.saveNonMCQ(instructorUserName, question);
                logger.info("Save Non-MCQ type question : " + question);
            } else {
                List<Option> optionList = options.getOptionList();
                question.setCreatedDate(createdDate);
                status = questionsDao.saveMultipleChoiceQuestion(question, optionList, instructorUserName);
                logger.info("Save MCQ type question with options : " + question);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Question> getAllTitles(String instructorUserName) {
        return questionsDao.findAllTitles(instructorUserName);
    }

    @Override
    public int deleteQuestion(String instructorUserName, Question question) {
        int status = 0;
        try {
            status = questionsDao.removeQuestion(instructorUserName, question);
            logger.info("Remove Question : " + question);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Question> getAllSortedTitlesByTitles(String instructorUserName) {
        return questionsDao.findAllSortedTitlesByTitles(instructorUserName);
    }

    @Override
    public List<Question> getAllSortedTitlesByDates(String instructorUserName) {
        List<Question> questions = questionsDao.findAllSortedTitlesByDates(instructorUserName);
        for (Question question : questions) {
            logger.info("Question Titles:" + question.getTitle());
        }
        return questions;
    }

    @Override
    public List<Question> getAllQuestions(String instructorUserName) {
        return questionsDao.findAllQuestions(instructorUserName);
    }

}