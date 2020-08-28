package com.csci5308.groupme;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.auth.config.PasswordEncryptionConfig;
import com.csci5308.groupme.auth.config.PasswordEncryptionConfigImpl;
import com.csci5308.groupme.auth.config.PasswordSecurityConfig;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDao;
import com.csci5308.groupme.course.courseadmin.instructor.dao.QuestionsDaoImpl;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentService;
import com.csci5308.groupme.course.courseadmin.instructor.service.EnrollmentServiceImpl;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerService;
import com.csci5308.groupme.course.courseadmin.instructor.service.QuestionManagerServiceImpl;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDao;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.dao.TeachingAssistantDaoImpl;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantService;
import com.csci5308.groupme.course.courseadmin.teaching_assistant.service.TeachingAssistantServiceImpl;
import com.csci5308.groupme.course.survey.dao.*;
import com.csci5308.groupme.course.survey.service.*;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDao;
import com.csci5308.groupme.passwordvalidation.dao.PasswordValidationDaoImpl;
import com.csci5308.groupme.passwordvalidation.service.PasswordValidationService;
import com.csci5308.groupme.passwordvalidation.service.PasswordValidationServiceImpl;

public class SystemConfig {

    private static SystemConfig uniqueInstance = null;
    private QuestionManagerService questionManagerService;
    private QuestionsDao questionsDao;
    private TeachingAssistantDao teachingAssistantDao;
    private TeachingAssistantService teachingAssistantService;
    private DatabaseProperties databaseProperties;
    private EnrollmentService enrollmentService;
    private GroupFormationService groupFormationService;
    private SurveyOperationService surveyOperationService;
    private SurveyOperationDao surveyOperationDao;
    private SurveyPublishDao surveyPublishDao;
    private SurveyPublishService surveyPublishService;
    private PasswordValidationService passwordValidationService;
    private PasswordValidationDao passwordValidationDao;
    private PasswordSecurityConfig passwordSecurityConfig;
    private SurveyCustomiseDao surveyCustomiseDao;
    private SurveyCustomiseService surveyCustomiseService;
    private PasswordEncryptionConfig passwordEncryptionConfig;

    private SystemConfig() {
        teachingAssistantDao = new TeachingAssistantDaoImpl();
        teachingAssistantService = new TeachingAssistantServiceImpl(teachingAssistantDao);
        questionsDao = new QuestionsDaoImpl();
        questionManagerService = new QuestionManagerServiceImpl(questionsDao);
        databaseProperties = new DatabaseProperties();
        enrollmentService = new EnrollmentServiceImpl();
        groupFormationService = new GroupFormationServiceImpl();
        surveyOperationDao = new SurveyOperationDaoImpl();
        surveyOperationService = new SurveyOperationServiceImpl(surveyOperationDao);
        surveyPublishDao = new SurveyPublishDaoImpl();
        surveyPublishService = new SurveyPublishServiceImpl(surveyPublishDao);
        passwordValidationDao = new PasswordValidationDaoImpl();
        passwordValidationService = new PasswordValidationServiceImpl(passwordValidationDao);
        passwordSecurityConfig = new PasswordSecurityConfig();
        surveyCustomiseDao = new SurveyCustomiseDaoImpl();
        surveyCustomiseService = new SurveyCustomiseServiceImpl(surveyCustomiseDao);
        passwordEncryptionConfig = new PasswordEncryptionConfigImpl();
    }

    public static SystemConfig instance() {
        if (null == uniqueInstance) {
            uniqueInstance = new SystemConfig();
        }
        return uniqueInstance;
    }

    public PasswordValidationDao getPasswordValidationDao() {
        return passwordValidationDao;
    }

    public void setPasswordValidationDao(PasswordValidationDao passwordValidationDao) {
        this.passwordValidationDao = passwordValidationDao;
    }

    public TeachingAssistantDao getTeachingAssistantDao() {
        return teachingAssistantDao;
    }

    public void setTeachingAssistantDao(TeachingAssistantDao teachingAssistantDao) {
        this.teachingAssistantDao = teachingAssistantDao;
    }

    public TeachingAssistantService getTeachingAssistantService() {
        return teachingAssistantService;
    }

    public void setTeachingAssistantService(TeachingAssistantService teachingAssistantService) {
        this.teachingAssistantService = teachingAssistantService;
    }

    public QuestionManagerService getQuestionManagerService() {
        return questionManagerService;
    }

    public void setQuestionManagerService(QuestionManagerService questionManagerService) {
        this.questionManagerService = questionManagerService;
    }

    public QuestionsDao getQuestionsDao() {
        return questionsDao;
    }

    public void setQuestionsDao(QuestionsDao questionsDao) {
        this.questionsDao = questionsDao;
    }

    public DatabaseProperties getDatabaseProperties() {
        return databaseProperties;
    }

    public void setDatabaseProperties(DatabaseProperties databaseProperties) {
        this.databaseProperties = databaseProperties;
    }

    public EnrollmentService getEnrollmentService() {
        return enrollmentService;
    }

    public void setEnrollmentService(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    public GroupFormationService getGroupFormationService() {
        return groupFormationService;
    }

    public void setGroupFormationService(GroupFormationService groupFormationService) {
        this.groupFormationService = groupFormationService;
    }

    public PasswordValidationService getPasswordValidationService() {
        return passwordValidationService;
    }

    public void setPasswordValidationService(PasswordValidationService passwordValidationService) {
        this.passwordValidationService = passwordValidationService;
    }

    public PasswordSecurityConfig getPasswordSecurityConfig() {
        return passwordSecurityConfig;
    }

    public void setPasswordSecurityConfig(PasswordSecurityConfig passwordSecurityConfig) {
        this.passwordSecurityConfig = passwordSecurityConfig;
    }

    public SurveyOperationService getSurveyOperationService() {
        return surveyOperationService;
    }

    public void setSurveyOperationService(SurveyOperationService surveyOperationService) {
        this.surveyOperationService = surveyOperationService;
    }

    public SurveyOperationDao getSurveyOperationDao() {
        return surveyOperationDao;
    }

    public void setSurveyOperationDao(SurveyOperationDao surveyOperationDao) {
        this.surveyOperationDao = surveyOperationDao;
    }

    public SurveyPublishDao getSurveyPublishDao() {
        return surveyPublishDao;
    }

    public void setSurveyPublishDao(SurveyPublishDao surveyPublishDao) {
        this.surveyPublishDao = surveyPublishDao;
    }

    public SurveyPublishService getSurveyPublishService() {
        return surveyPublishService;
    }

    public void setSurveyPublishService(SurveyPublishService surveyPublishService) {
        this.surveyPublishService = surveyPublishService;
    }

    public SurveyCustomiseDao getSurveyCustomiseDao() {
        return surveyCustomiseDao;
    }

    public void setSurveyCustomiseDao(SurveyCustomiseDao surveyCustomiseDao) {
        this.surveyCustomiseDao = surveyCustomiseDao;
    }

    public SurveyCustomiseService getSurveyCustomiseService() {
        return surveyCustomiseService;
    }

    public void setSurveyCustomiseService(SurveyCustomiseService surveyCustomiseService) {
        this.surveyCustomiseService = surveyCustomiseService;
    }

    public PasswordEncryptionConfig getPasswordEncryptionConfig() {
        return passwordEncryptionConfig;
    }

    public void setPasswordEncryptionConfig(PasswordEncryptionConfig passwordEncryptionConfig) {
        this.passwordEncryptionConfig = passwordEncryptionConfig;
    }

}

