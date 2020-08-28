package com.csci5308.groupme.course.courseadmin.instructor.model;

import java.sql.Date;

public class Question {

    private Integer questionId;
    private String title;
    private String question;
    private String type;
    private Date createdDate;

    public Question() {
        this.questionId = 0;
        this.title = null;
        this.question = null;
        this.type = null;
        this.createdDate = null;
    }

    public Question(String questionTitle) {
        this.title = questionTitle;
    }

    public Question(String questionTitle, Integer questionId, String question) {
        this.title = questionTitle;
        this.questionId = questionId;
        this.question = question;
    }

    public Question(String questionTitle, Integer questionId, Date questionDate) {
        this.title = questionTitle;
        this.questionId = questionId;
        this.createdDate = questionDate;
    }

    public Question(String questionTitle, Integer questionId, String question, String questionType) {
        this.title = questionTitle;
        this.questionId = questionId;
        this.question = question;
        this.type = questionType;
    }

    public Question(String questionTitle, Integer questionId, Date questionDate, String question, String questionType) {
        this.title = questionTitle;
        this.questionId = questionId;
        this.createdDate = questionDate;
        this.question = question;
        this.type = questionType;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreatedDate() {

        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
