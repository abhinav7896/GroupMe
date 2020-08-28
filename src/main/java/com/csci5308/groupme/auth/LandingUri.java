package com.csci5308.groupme.auth;

public class LandingUri {

    public static final String ADMIN = "/admin";
    public static final String GUEST = "/guest";
    public static final String STUDENT = "/home?isStudent=true";
    public static final String TA = "/home?isTA=true";
    public static final String INSTRUCTOR = "/home?isInstructor=true";
    public static final String STU_TA = "/home?isStudent=true&isTA=true";
    public static final String STU_INST = "/home?isStudent=true&isInstructor=true";
    public static final String TA_INST = "/home?isTA=true&isInstructor=true";
    public static final String STU_TA_INST = "/home?isStudent=true&isTA=true&isInstructor=true";

}
