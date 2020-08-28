package com.csci5308.groupme.course.survey.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SurveyPublishDaoImpl implements SurveyPublishDao {

    private final Logger logger = LoggerFactory.getLogger(SurveyPublishDaoImpl.class);
    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    DatabaseProperties databaseProperties;

    @Override
    public int publishSurveyForStudents(String roleName, String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        Integer surveyPublishStatus = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            if (roleName.equals(Roles.INSTRUCTOR)) {
                logger.debug("PUBLISH_SURVEY procedure called");
                callableStatement = connection.prepareCall("{call PUBLISH_SURVEY(?,?)}");
                callableStatement.setString(1, courseCode);
                callableStatement.registerOutParameter(2, Types.INTEGER);
            }
            int rowCount = callableStatement.executeUpdate();
            logger.info("{} survey published", rowCount);
            surveyPublishStatus = callableStatement.getInt(2);
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
        return surveyPublishStatus;
    }
}

