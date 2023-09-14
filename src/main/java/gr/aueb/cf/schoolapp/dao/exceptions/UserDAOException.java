package gr.aueb.cf.schoolapp.dao.exceptions;

import java.sql.SQLException;

public class UserDAOException extends Exception{
    private static final long serialVersionUID = 123456L;

    public UserDAOException(String s) {
        super(s);
    }

    public UserDAOException(String s, SQLException e) {
    }

    public UserDAOException(String errorValidatingUser, Exception e) {
    }
}
