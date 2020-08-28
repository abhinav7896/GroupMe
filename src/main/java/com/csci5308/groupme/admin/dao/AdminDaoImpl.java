package com.csci5308.groupme.admin.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.user.service.UserServiceImpl;
import errors.EditCodes;
import errors.SqlErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.ClassQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class AdminDaoImpl implements AdminDao {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private DatabaseProperties databaseProperties;

    @Override
    public int createClass(String instructorUserName, String courseCode) throws Exception {
        int insertStatus = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        try {
            String DRIVER = databaseProperties.getDriver();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(ClassQuery.CREATE_CLASS);
            preparedStatement.setString(1, instructorUserName);
            preparedStatement.setString(2, courseCode);
            insertStatus = preparedStatement.executeUpdate();
        } catch (SQLException se) {
            logger.info(se.getMessage());
            if (se.getErrorCode() == SqlErrors.DUPLICATE_ENTRY) {
                return EditCodes.CLASS_ALREADY_CREATED;
            }
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();

                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (connection != null)
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return insertStatus;
    }
}
