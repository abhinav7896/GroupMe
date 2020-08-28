package com.csci5308.groupme.course.courseadmin.teaching_assistant.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import errors.EditCodes;
import org.slf4j.LoggerFactory;
import sql.UserQuery;

import java.sql.*;

public class TeachingAssistantDaoImpl implements TeachingAssistantDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(TeachingAssistantDaoImpl.class);

    @Override
    public int createTAForCourse(String emailId, String courseCode) throws Exception {
        int status = 0;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            DatabaseProperties databaseProperties = SystemConfig.instance().getDatabaseProperties();
            String JDBC_DRIVER = databaseProperties.getDriver();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            Class.forName(JDBC_DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(UserQuery.FIND_BY_EMAIL);
            preparedStatement.setString(1, emailId);
            resultSet = preparedStatement.executeQuery();
            logger.debug("FIND_BY_EMAIL Query executed");
            if (resultSet.next() == false) {
                return EditCodes.EMAIL_DOES_NOT_EXIST;
            }
            do {
                String taUserName = resultSet.getString("userName");
                CallableStatement callableStatementToAddTA = connection.prepareCall("{call INSERT_TA(?,?)}");
                try {
                    if (callableStatementToAddTA != null) {
                        callableStatementToAddTA.setString(1, taUserName);
                        callableStatementToAddTA.setString(2, courseCode);
                        callableStatementToAddTA.executeUpdate();
                        logger.debug("INSERT_TA procedure called to assign TA to course");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    callableStatementToAddTA.close();
                    logger.debug("Callable Statement to add TA closed");
                }
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    logger.debug("Result set closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    logger.debug("Prepared Statement closed");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database Closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }

}
