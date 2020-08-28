package sql;

public class CourseQuery {

    public static final String SELECT_ALL_COURSE = "SELECT * FROM course";

    public static final String SELECT_COURSES_BY_USERNAME_TA = "SELECT courseCode FROM courseadmin WHERE taUserName=?";

    public static final String SELECT_COURSES_BY_USERNAME_INSTRUCTOR = "SELECT courseCode FROM courseadmin WHERE instructorUserName=?";

    public static final String SELECT_COURSE_BY_COURSE_CODE = "SELECT * FROM course WHERE courseCode=?";

    public static final String SELECT_COURSE_STUDENT_ENROLLED_IN_BY_USERNAME_STUDENT = "SELECT DISTINCT courseCode FROM enrollment WHERE studentUserName=?";

    public static final String ADD_COURSE = "INSERT INTO course(courseCode, courseName, crn) VALUES(?,?,?)";

    public static final String DELETE_COURSE = "DELETE FROM course WHERE courseCode=?";

    public static final String SELECT_COURSE_USERNAME_INSTRUCTOR = "select courseName, c.courseCode, crn from course inner join class c on course.courseCode = c.courseCode where instructorUserName=?;";
}
