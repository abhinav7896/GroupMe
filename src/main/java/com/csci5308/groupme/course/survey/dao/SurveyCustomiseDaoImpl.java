package com.csci5308.groupme.course.survey.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SurveyCustomiseDaoImpl implements SurveyCustomiseDao {

    private final Logger logger = LoggerFactory.getLogger(SurveyOperationDaoImpl.class);
    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    DatabaseProperties databaseProperties;

    @Override
    public String getSurveyQuestions(String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        String surveyQuestions = null;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("GET_SURVEY_QUESTIONS procedure called");
            callableStatement = connection.prepareCall("{call GET_SURVEY_QUESTIONS(?)}");
            callableStatement.setString(1, courseCode);
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                surveyQuestions = resultSet.getString("questions");
            } while (resultSet.next());
            logger.info("Survey Questions: " + surveyQuestions);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("ResultSet Closed");
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("CallableStatement Closed");
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return surveyQuestions;
    }

    @Override
    public int checkIfSurveyIsPublished(String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        Integer surveyIsPublished = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("CHECK_IF_SURVEY_IS_PUBLISHED procedure called");
            callableStatement = connection.prepareCall("{call CHECK_IF_SURVEY_IS_PUBLISHED(?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.executeUpdate();
            surveyIsPublished = callableStatement.getInt(2);
            logger.info("Survey Status (0 : Not published) or (1: published) " + surveyIsPublished);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("ResultSet Closed");
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("CallableStatement Closed");
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return surveyIsPublished;
    }

    @Override
    public int insertCustomisedQuestionsToSurvey(String courseCode, String newSurveyQuestions) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        Integer rowCount = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("INSERT_CUSTOMISED_SURVEY procedure called");
            callableStatement = connection.prepareCall("{call INSERT_CUSTOMISED_SURVEY(?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.setString(2, newSurveyQuestions);
            rowCount = callableStatement.executeUpdate();
            logger.info("Inserted customised survey questions for course: " + courseCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("ResultSet Closed");
            }
            if (callableStatement != null) {
                try {
                    callableStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("CallableStatement Closed");
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return rowCount;
    }
}
