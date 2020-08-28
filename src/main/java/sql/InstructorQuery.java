package sql;

public class InstructorQuery {

    public static final String GET_INSTRUCTOR_BY_USERNAME = "SELECT * FROM instructor where userName=?";

    public static final String SAVE_INSTRUCTOR = "INSERT INTO instructor(userName, about) VALUES(?,?)";

}
