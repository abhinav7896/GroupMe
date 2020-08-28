package com.csci5308.groupme.course.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.model.Course;
import errors.EditCodes;
import errors.SqlErrors;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.CourseQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(CourseDaoImpl.class);
    DatabaseProperties databaseProperties;

    @Override
    public int save(Course course) {
        int rowCount = 0;
        Connection connection = null;
        PreparedStatement saveCourseStatement = null;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connection successful to database...");
            saveCourseStatement = connection.prepareStatement(CourseQuery.ADD_COURSE);
            saveCourseStatement.setString(1, course.getCourseCode());
            saveCourseStatement.setString(2, course.getCourseName());
            saveCourseStatement.setInt(3, course.getCourseCrn());
            rowCount = saveCourseStatement.executeUpdate();
            logger.info("Course added: " + course.getCourseName());
            logger.debug("ADD_COURSE query called course added : " + course.getCourseName());
            if (rowCount == 0) {
                return 0;
            }
        } catch (SQLException se) {
            if (se.getErrorCode() == SqlErrors.DUPLICATE_ENTRY)
                return EditCodes.COURSE_EXISTS;
            se.printStackTrace();
        } finally {
            if (saveCourseStatement != null) {
                try {
                    saveCourseStatement.close();
                    logger.debug("saveCourseStatement closed..");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to the database closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return rowCount;
    }

    @Override
    public int remove(String courseCode) throws Exception {
        Connection connection = null;
        PreparedStatement deleteCourseStatement = null;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        int rowCount = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connection successful to database...");
            deleteCourseStatement = connection.prepareStatement(CourseQuery.DELETE_COURSE);
            deleteCourseStatement.setString(1, courseCode);
            rowCount = deleteCourseStatement.executeUpdate();
            logger.info("Course deleted with code: " + courseCode);
            if (rowCount == 0) {
                return EditCodes.COURSE_DOES_NOT_EXIST;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (deleteCourseStatement != null) {
                try {
                    deleteCourseStatement.close();
                    logger.debug("deleteCourseStatement closed...");
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
        return rowCount;
    }

}
