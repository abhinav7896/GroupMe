package com.csci5308.groupme.course.courseadmin.instructor.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.Instructor;
import errors.EditCodes;
import errors.SqlErrors;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.InstructorQuery;

import java.sql.*;

@Repository
public class InstructorDaoImpl implements InstructorDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(InstructorDaoImpl.class);
    private DatabaseProperties databaseProperties;
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    @Override
    public Instructor findByUserName(String userName) throws Exception {
        Instructor instructor = new Instructor();
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
            logger.debug("GET_INSTRUCTOR_BY_USERNAME Query executed");
            preparedStatement = connection.prepareStatement(InstructorQuery.GET_INSTRUCTOR_BY_USERNAME);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                instructor.setUserName(resultSet.getString("userName"));
                instructor.setAbout(resultSet.getString("about"));
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return instructor;
    }

    @Override
    public int save(Instructor instructor) throws Exception {
        int status = 0;
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
            logger.debug("SAVE_INSTRUCTOR Query executed");
            preparedStatement = connection.prepareStatement(InstructorQuery.SAVE_INSTRUCTOR);
            preparedStatement.setString(1, instructor.getUserName());
            preparedStatement.setString(2, instructor.getAbout());
            status = preparedStatement.executeUpdate();
        } catch (SQLException se) {
            logger.info(se.getMessage());
            String duplicateKey = se.getMessage().split(" ")[5];
            logger.info(duplicateKey);
            if (se.getErrorCode() == SqlErrors.DUPLICATE_ENTRY && duplicateKey.equalsIgnoreCase("'PRIMARY'")) {
                return EditCodes.USERNAME_EXISTS;
            }
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
            } catch (SQLException se) {
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return status;
    }
}
