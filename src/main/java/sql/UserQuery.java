package sql;

public class UserQuery {

    public static final String ADD_USER = "INSERT INTO user(userName, firstName, lastName, email, password) VALUES (?,?,?,?,?)";

    public static final String ADD_USERROLE = "INSERT INTO userrole(userName, roleName) VALUES (?, ?)";

    public static final String FIND_BY_USERNAME = "SELECT * FROM user WHERE userName = ?";

    public static final String FIND_ROLES_BY_USERNAME = "SELECT roleName FROM userrole WHERE userName=?";

    public static final String FIND_BY_EMAIL = "SELECT * FROM user WHERE email = ?";

    public static final String FIND_BY_NAME = "SELECT * FROM user WHERE firstName = ? and lastName = ?";

    public static final String UPDATE_USER = "UPDATE user SET firstName = ?, lastName = ?, email = ?, password = ? "
            + "WHERE userName = ?";

    public static final String ADD_USER_IN_PASSWORD_HISTORY = "INSERT INTO user_password_history (userName, password, timestamp) VALUES (?,?,?)";
}
