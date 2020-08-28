package sql;

public class QuestionsQuery {

    public static final String SAVE_QUESTION = "INSERT INTO questions_manager(instructorUserName, questionTitle, questionType, question, dateCreated) VALUES(?,?,?,?,?)";

    public static final String GET_QUESTION_TITLE = "SELECT distinct questionTitle, questionId, dateCreated FROM questions_manager where instructorUserName=? group by questionTitle";

    public static final String GET_SORTED_TITLE = "SELECT distinct questionTitle from questions_manager where instructorUserName=? order by questionTitle";

    public static final String GET_TITLES_SORTED_BY_DATE = "Select dateCreated, questionTitle from questions_manager where instructorUserName=? group by questionTitle order by dateCreated";

    public static final String GET_ALL_QUESTIONS = "SELECT * from questions_manager where instructorUserName=?";

    public static final String DELETE_QUESTION = "DELETE from questions_manager where questionId =?";
}