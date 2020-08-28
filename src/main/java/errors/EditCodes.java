package errors;

public class EditCodes {

	public static final int DEFAULT = 0;
	
	public static final int FAILURE = -1;
	
    public static final int USERNAME_EXISTS = -2;

    public static final int EMAIL_EXISTS = -4;

    public static final int EMAIL_DOES_NOT_EXIST = -5;

    public static final int COURSE_EXISTS = -6;

    public static final int COURSE_DOES_NOT_EXIST = -7;

    public static final int CLASS_ALREADY_CREATED = -8;

    public static final int USERROLE_EXISTS = -9;

    public static final int SUCCESS = 1;

    public static final int INSTRUCTOR_NOT_CREATED = -11;

    public static final int GROUP_SIZE_IS_ZERO = -12;

    public static final int GROUP_SIZE_GREATER_THAN_STRENGTH = -13;

}
