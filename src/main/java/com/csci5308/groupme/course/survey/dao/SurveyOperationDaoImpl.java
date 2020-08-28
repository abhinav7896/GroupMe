package com.csci5308.groupme.course.survey.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.SystemConfig;
import com.csci5308.groupme.course.courseadmin.instructor.model.Question;
import com.csci5308.groupme.course.survey.constants.SurveyConstants;
import com.csci5308.groupme.course.survey.model.Candidate;
import constants.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class SurveyOperationDaoImpl implements SurveyOperationDao {

    private final Logger logger = LoggerFactory.getLogger(SurveyOperationDaoImpl.class);
    Connection connection = null;
    ResultSet resultSet = null;
    CallableStatement callableStatement = null;
    DatabaseProperties databaseProperties;

    @Override
    public List<Question> showQuestionsOnCreateSurveyPage(String userName, String courseCode, String roleName) {

        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Question> notAddedQuestionsList = null;
        try {
            notAddedQuestionsList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            if (roleName.equals(Roles.TA)) {
                logger.debug("FIND_QUESTIONS_BY_TA_ROLE procedure called");
                callableStatement = connection.prepareCall("{call FIND_QUESTIONS_BY_TA_ROLE(?,?)}");
                callableStatement.setString(1, userName);
                callableStatement.setString(2, courseCode);
            } else {
                logger.debug("FIND_QUESTIONS_BY_INSTRUCTOR_ROLE procedure called");
                callableStatement = connection.prepareCall("{call FIND_QUESTIONS_BY_INSTRUCTOR_ROLE(?)}");
                callableStatement.setString(1, userName);
            }
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                Integer questionId = resultSet.getInt("questionId");
                String questionTitle = resultSet.getString("questionTitle");
                String questionType = resultSet.getString("questionType");
                String question = resultSet.getString("question");
                notAddedQuestionsList.add(new Question(questionTitle, questionId, question, questionType));
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
                logger.info("ResultSet Closed");
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
        }
        return notAddedQuestionsList;
    }

    @Override
    public String getJsonObjectOfQuestions(String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        String surveyQuestions = null;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("GET_JSON_QUESTION_DATA procedure called");
            callableStatement = connection.prepareCall("{call GET_JSON_QUESTION_DATA(?)}");
            callableStatement.setString(1, courseCode);
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                surveyQuestions = resultSet.getString("questions");
            } while (resultSet.next());
            logger.info("surveyQuestions :" + surveyQuestions);
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
        }
        return surveyQuestions;
    }

    @Override
    public int insertQuestionToSurvey(String courseCode, String jsonString, Integer questionId) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        int rowCount = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("INSERT_SURVEY_QUESTION procedure called");
            callableStatement = connection.prepareCall("{call INSERT_SURVEY_QUESTION(?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.setString(2, jsonString);
            rowCount = callableStatement.executeUpdate();
            logger.debug("UPDATE_QUESTION_FLAG procedure called");
            callableStatement = connection.prepareCall("{call UPDATE_QUESTION_FLAG(?)}");
            callableStatement.setInt(1, questionId);
            callableStatement.executeUpdate();
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
        }
        return rowCount;
    }

    @Override
    public int deleteQuestionFromSurvey(String courseCode, String jsonString, Integer questionId) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        int rowCount = 0;
        try {
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("INSERT_SURVEY_QUESTION procedure called");
            callableStatement = connection.prepareCall("{call INSERT_SURVEY_QUESTION(?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.setString(2, jsonString);
            rowCount = callableStatement.executeUpdate();
            logger.debug("REMOVE_QUESTION_FROM_SURVEY procedure called");
            callableStatement = connection.prepareCall("{call REMOVE_QUESTION_FROM_SURVEY(?)}");
            callableStatement.setInt(1, questionId);
            callableStatement.executeUpdate();
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
        }
        return rowCount;
    }

    public Map<String, Integer> checkIfSurveyExist(String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        Integer surveyId;
        Integer isPublished;
        Map<String, Integer> conditions = null;
        try {
            conditions = new HashMap<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("CHECK_IF_SURVEY_EXIST procedure called");
            callableStatement = connection.prepareCall("{call CHECK_IF_SURVEY_EXIST(?,?,?)}");
            callableStatement.setString(1, courseCode);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3, Types.INTEGER);
            callableStatement.executeUpdate();
            surveyId = callableStatement.getInt(2);
            isPublished = callableStatement.getInt(3);
            if (surveyId == SurveyConstants.DEFAULT_SURVEY_ID) {
                logger.debug("CREATE_SURVEY procedure called");
                callableStatement = connection.prepareCall("{call CREATE_SURVEY(?)}");
                callableStatement.setString(1, courseCode);
                callableStatement.executeUpdate();
            }
            conditions.put("surveyId", surveyId);
            conditions.put("isPublished", isPublished);
            logger.info("Survey Id: {} And is published on not: {} ", surveyId, isPublished);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
        return conditions;
    }

    @Override
    public List<Question> getAlreadyAddedSurveyQuestions(String userName, String roleName, String courseCode) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Question> addedQuestionsList = null;
        try {
            addedQuestionsList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            if (roleName.equals(Roles.TA)) {
                logger.debug("GET_ADDED_SURVEY_QUESTIONS_BY_TA procedure called");
                callableStatement = connection.prepareCall("{call GET_ADDED_SURVEY_QUESTIONS_BY_TA(?,?)}");
                callableStatement.setString(1, userName);
                callableStatement.setString(2, courseCode);
            } else {
                logger.debug("GET_ADDED_SURVEY_QUESTIONS_BY_INSTRUCTOR procedure called");
                callableStatement = connection.prepareCall("{call GET_ADDED_SURVEY_QUESTIONS_BY_INSTRUCTOR(?)}");
                callableStatement.setString(1, userName);
            }
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                Integer questionId = resultSet.getInt("questionId");
                String questionTitle = resultSet.getString("questionTitle");
                String questionType = resultSet.getString("questionType");
                String question = resultSet.getString("question");
                addedQuestionsList.add(new Question(questionTitle, questionId, question, questionType));
                logger.info("Question added to list:{} and its question Id: {} ", question, questionId);
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
        return addedQuestionsList;
    }

    @Override
    public List<Candidate> getSurveyResponses(Integer surveyIdToGetResponses) {
        databaseProperties = SystemConfig.instance().getDatabaseProperties();
        List<Candidate> candidateList = null;
        try {
            candidateList = new ArrayList<>();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            logger.info("Connecting to database");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to database successfully...");
            logger.debug("GET_SURVEY_RESPONSES procedure called");
            callableStatement = connection.prepareCall("{call GET_SURVEY_RESPONSES(?)}");
            callableStatement.setInt(1, surveyIdToGetResponses);
            resultSet = callableStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                Integer surveyId = resultSet.getInt("surveyId");
                String userName = resultSet.getString("studentUserName");
                String stringifiedResponses = resultSet.getString("responses");
                String bannerId = resultSet.getString("bannerId");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                Candidate candidate = new Candidate();
                candidate.setSurveyId(surveyId);
                candidate.setUserName(userName);
                candidate.setStringifiedResponses(stringifiedResponses);
                candidate.setBannerId(bannerId);
                candidate.setFirstName(firstName);
                candidate.setLastName(lastName);
                candidateList.add(candidate);
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
        return candidateList;
    }
}

