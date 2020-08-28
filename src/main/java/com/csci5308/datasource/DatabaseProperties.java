package com.csci5308.datasource;

public class DatabaseProperties {

    private String dbDriver;
    private String dbURL;
    private String dbUserName;
    private String dbPassword;

    public DatabaseProperties() {
        try {
            dbDriver = System.getenv("DB_DRIVER");
            dbURL = System.getenv("DB_URL");
            dbUserName = System.getenv("DB_USERNAME");
            dbPassword = System.getenv("DB_PASSWORD");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getDbURL() {
        return dbURL;
    }

    public void setDbURL(String dbURL) {
        this.dbURL = dbURL;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDriver() {
        return dbDriver;
    }


}
