package com.csci5308.groupme.user.dao;

import com.csci5308.datasource.DatabaseProperties;
import com.csci5308.groupme.user.model.User;
import constants.Roles;
import errors.EditCodes;
import errors.SqlErrors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import sql.UserQuery;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
    DatabaseProperties databaseProperties = new DatabaseProperties();
    String DB_URL = databaseProperties.getDbURL();
    String USER = databaseProperties.getDbUserName();
    String PASS = databaseProperties.getDbPassword();
    String JDBC_DRIVER = databaseProperties.getDriver();
    private Connection connection = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet;

    @Override
    public User findByUserName(String userName) {
        User user = new User();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = connection.prepareStatement(UserQuery.FIND_BY_USERNAME);
            preparedStatement.setString(1, userName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                user.setUserName(resultSet.getString("userName"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            } while (resultSet.next());
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            preparedStatement = connection.prepareStatement(UserQuery.FIND_ROLES_BY_USERNAME);
            preparedStatement.setString(1, userName);
            resultSet = preparedStatement.executeQuery();
            List<String> roles = new ArrayList<String>();
            while (resultSet.next()) {
                roles.add(resultSet.getString("roleName"));
            }
            user.setRoles(roles);
        } catch (SQLException se) {
            se.printStackTrace();
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
        return user;
    }

    @Override
    public User findByEmail(String email) {
        User user = new User();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = connection.prepareStatement(UserQuery.FIND_BY_EMAIL);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next() == false) {
                return null;
            }
            do {
                user.setUserName(resultSet.getString("userName"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
            } while (resultSet.next());
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            preparedStatement = connection.prepareStatement(UserQuery.FIND_ROLES_BY_USERNAME);
            preparedStatement.setString(1, user.getUserName());
            resultSet = preparedStatement.executeQuery();
            List<String> roles = new ArrayList<String>();
            while (resultSet.next()) {
                roles.add(resultSet.getString("roleName"));
            }

            user.setRoles(roles);
        } catch (SQLException se) {
            se.printStackTrace();
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
        return user;
    }

    @Override
    public List<User> findByName(String firstName, String lastName) {
        User user = new User();
        List<User> users = new ArrayList<User>();
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            preparedStatement = connection.prepareStatement(UserQuery.FIND_BY_NAME);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                user.setUserName(resultSet.getString("userName"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmail(resultSet.getString("email"));
                user.setPassword(resultSet.getString("password"));
                users.add(user);
            }
        } catch (SQLException se) {
            se.printStackTrace();
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
        return users;
    }

    @Override
    public int save(User user) {
        int addedUserCount = 0;
        long millis;
        Date createdDate;
        try {
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UserQuery.ADD_USER);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getFirstName());
            preparedStatement.setString(3, user.getLastName());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            addedUserCount = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(UserQuery.ADD_USERROLE);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, Roles.GUEST);
            addedUserCount = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(UserQuery.ADD_USER_IN_PASSWORD_HISTORY);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            millis = System.currentTimeMillis();
            createdDate = new Date(millis);
            preparedStatement.setDate(3, createdDate);
            addedUserCount = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException se) {
            try {
                connection.rollback();
                logger.info(se.getMessage());
                String duplicateKey = se.getMessage().split(" ")[5];
                logger.info(duplicateKey);
                if (se.getErrorCode() == SqlErrors.DUPLICATE_ENTRY && duplicateKey.equalsIgnoreCase("'PRIMARY'")) {
                    connection.close();
                    return EditCodes.USERNAME_EXISTS;
                }
            } catch (SQLException e) {
                e.printStackTrace();
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return addedUserCount;
    }

    @Override
    public int addRole(String userName, String roleName) {
        int status = 0;
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UserQuery.ADD_USERROLE);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, roleName);
            status = preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException se) {
            try {
                connection.rollback();
                if (se.getErrorCode() == SqlErrors.DUPLICATE_ENTRY) {
                    connection.close();
                    return EditCodes.USERROLE_EXISTS;
                }
            } catch (SQLException e) {
                e.printStackTrace();
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

    @Override
    public int update(User user) {
        int updatedRowCount = 0;
        long millis;
        Date createdDate;
        try {
            Class.forName(JDBC_DRIVER);
            logger.info("Connecting to the selected database...");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(UserQuery.UPDATE_USER);
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserName());
            updatedRowCount = preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(UserQuery.ADD_USER_IN_PASSWORD_HISTORY);
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getPassword());
            millis = System.currentTimeMillis();
            createdDate = new Date(millis);
            preparedStatement.setDate(3, createdDate);
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException se) {
            try {
                connection.rollback();
                logger.info(se.getMessage());
            } catch (SQLException e) {
                e.printStackTrace();
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
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return updatedRowCount;
    }

}
