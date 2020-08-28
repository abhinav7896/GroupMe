package com.csci5308.groupme.course.dao;

import ch.qos.logback.classic.Logger;
import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.model.Course;
import constants.Roles;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.CourseQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDetailsDaoImpl implements CourseDetailsDao {

    private final Logger logger = (Logger) LoggerFactory.getLogger(CourseDetailsDaoImpl.class);
    DatabaseProperties databaseProperties;

    @Override
    public List<Course> findAllCourses() throws Exception {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Course> retrievedCourseList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            retrievedCourseList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(CourseQuery.SELECT_ALL_COURSE);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                String courseName = resultSet.getString("courseName");
                String courseCode = resultSet.getString("courseCode");
                Integer courseCrn = resultSet.getInt("crn");
                retrievedCourseList.add(new Course(courseCode, courseName, courseCrn));
                logger.info("Course retrieved: " + courseName);

            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    logger.debug("Result set closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    logger.debug("Prepared Statement closed...");
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
        return retrievedCourseList;
    }

    @Override
    public List<Course> getCoursesByUserNameAndRole(String userName, String roleName) throws Exception {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Course> courseAdminCoursesList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            courseAdminCoursesList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            if (roleName.equals(Roles.TA)) {
                preparedStatement = connection.prepareStatement(CourseQuery.SELECT_COURSES_BY_USERNAME_TA);
                preparedStatement.setString(1, userName);
                resultSet = preparedStatement.executeQuery();
                logger.info("Select Course by TA username");
            } else if (roleName.equals(Roles.INSTRUCTOR)) {
                preparedStatement = connection.prepareStatement(CourseQuery.SELECT_COURSES_BY_USERNAME_INSTRUCTOR);
                preparedStatement.setString(1, userName);
                resultSet = preparedStatement.executeQuery();
                logger.info("Select Course by Instructor username");
            }
            if (resultSet.next() == false) {
                return null;
            }
            do {
                PreparedStatement getCourseStatement = null;
                ResultSet courseResultSet = null;
                try {
                    String retrievedCourseCode = resultSet.getString("courseCode");
                    getCourseStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_BY_COURSE_CODE);
                    getCourseStatement.setString(1, retrievedCourseCode);
                    courseResultSet = getCourseStatement.executeQuery();
                    if (courseResultSet.next() == false) {
                        return null;
                    }
                    do {
                        String courseCode = resultSet.getString("courseCode");
                        Course course = findCourseByCourseCode(courseCode);
                        courseAdminCoursesList.add(course);
                    } while (courseResultSet.next());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (courseResultSet != null) {
                        try {
                            courseResultSet.close();
                            logger.debug("Course result set closed...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (getCourseStatement != null) {
                        try {
                            getCourseStatement.close();
                            logger.debug("Get course statement closed...");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    logger.debug("Result set closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    logger.debug("Prepared statement closed...");
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
        return courseAdminCoursesList;
    }

    @Override
    public List<Course> findCoursesByStudentUserName(String studentUserName) throws Exception {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Course> retrievedCourseList = null;
        ResultSet resultSet = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            retrievedCourseList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection
                    .prepareStatement(CourseQuery.SELECT_COURSE_STUDENT_ENROLLED_IN_BY_USERNAME_STUDENT);
            preparedStatement.setString(1, studentUserName);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                String courseCode = resultSet.getString("courseCode");
                Course course = findCourseByCourseCode(courseCode);
                retrievedCourseList.add(course);
            } while (resultSet.next());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                    logger.debug("Result set closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                    logger.debug("Prepared statement closed...");
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
        return retrievedCourseList;
    }

    public Course findCourseByCourseCode(String courseCode) throws Exception {
        ResultSet courseResultSet = null;
        Connection connection = null;
        PreparedStatement getCourseStatement = null;
        Course course = new Course();
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            getCourseStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_BY_COURSE_CODE);
            getCourseStatement.setString(1, courseCode);
            courseResultSet = getCourseStatement.executeQuery();
            if (courseResultSet.next() == false) {
                return null;
            }
            String courseName = courseResultSet.getString("courseName");
            Integer courseCrn = courseResultSet.getInt("crn");
            course = new Course(courseCode, courseName, courseCrn);
            logger.info("Course found by course code: " + courseCode);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (courseResultSet != null) {
                try {
                    courseResultSet.close();
                    logger.debug("Result set closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (getCourseStatement != null) {
                try {
                    getCourseStatement.close();
                    logger.debug("Prepared statement closed...");
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
        return course;
    }

    @Override
    public List<Course> findCoursesByInstructor(String instructorUserName) throws Exception {
        List<Course> courseList = new ArrayList<>();
        ResultSet courseResultSet = null;
        Connection connection = null;
        PreparedStatement getCourseStatement = null;
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            getCourseStatement = connection.prepareStatement(CourseQuery.SELECT_COURSE_USERNAME_INSTRUCTOR);
            getCourseStatement.setString(1, instructorUserName);
            courseResultSet = getCourseStatement.executeQuery();
            while (courseResultSet.next()) {
                Course course = new Course();
                course.setCourseName(courseResultSet.getString("courseName"));
                course.setCourseCode(courseResultSet.getString("courseCode"));
                course.setCourseCrn(courseResultSet.getInt("crn"));
                courseList.add(course);
                logger.info("Found Course: " + courseResultSet.getString("courseName")
                        + " By Instructor username: " + instructorUserName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (courseResultSet != null) {
                try {
                    courseResultSet.close();
                    logger.debug("Result set closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (getCourseStatement != null) {
                try {
                    getCourseStatement.close();
                    logger.debug("Statement closed for course");
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
        return courseList;
    }
}
