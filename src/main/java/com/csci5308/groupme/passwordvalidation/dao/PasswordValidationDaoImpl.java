package com.csci5308.groupme.passwordvalidation.dao;
/**
 * @author Krutarth Patel
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.csci5308.datasource.DatabaseProperties;

import sql.PasswordValidationQuery;

public class PasswordValidationDaoImpl implements PasswordValidationDao{
	
	Logger logger = LoggerFactory.getLogger(PasswordValidationDaoImpl.class);
    private Connection connection = null;
    
    @Override
	public HashMap<Long, String> loadActivePasswordValidators() {
		
		HashMap<Long, String> activeValidators = new HashMap<Long, String>();
		ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
            String DRIVER = databaseProperties.getDriver();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(PasswordValidationQuery.GET_ENABLED_PASSWORD_VALIDATORS);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do
            {
            	long validatorId = resultSet.getLong(1);
            	String name = resultSet.getString(2);
            	logger.info("Enabled validator id: " + validatorId + ",name: " + name);
            	activeValidators.put(validatorId, name);
            }
            while(resultSet.next());
        }
        catch(Exception e){
        	e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return activeValidators;
	}

    @Override
	public String loadConstraintByValidatorId(long validatorId) {
		
		String constraint = null;
		ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
            String DRIVER = databaseProperties.getDriver();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(PasswordValidationQuery.GET_CONSTRAINT_BY_VALIDATOR_ID);
            preparedStatement.setLong(1, validatorId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            	do
				{
            		constraint = resultSet.getString(1);
            		logger.info("Constraint value loaded is " + constraint);
				}
            	while (resultSet.next());
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        finally {
            try {
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return constraint;
	}

	@Override
	public List<String> getPreviousPasswordsByUsername(String userName, int constraint) {
		List<String> passwordList = new ArrayList<String>();
		ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        DatabaseProperties databaseProperties = new DatabaseProperties();
        try {
            String DRIVER = databaseProperties.getDriver();
            String DB_URL = databaseProperties.getDbURL();
            String USER = databaseProperties.getDbUserName();
            String PASSWORD = databaseProperties.getDbPassword();
            Class.forName(DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
            logger.info("Connected to the database successfully...");
            preparedStatement = connection.prepareStatement(PasswordValidationQuery.GET_PREVIOUS_PASSWORDS);
            preparedStatement.setString(1, userName);
            preparedStatement.setInt(2, constraint);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            	do
				{
            		passwordList.add(resultSet.getString(1));
				}
            	while (resultSet.next());
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		finally {
            try {
                resultSet.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                    logger.info("Connection to database closed...");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
		}
        return passwordList;
	}
}


