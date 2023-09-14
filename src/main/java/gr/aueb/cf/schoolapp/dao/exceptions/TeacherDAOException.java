package gr.aueb.cf.schoolapp.dao.exceptions;

import java.sql.SQLException;

public class TeacherDAOException extends Exception {
    private static final long serialVersionUID = 123456L;

    public TeacherDAOException(String s) {
        super(s);
    }

    public TeacherDAOException(String sqlErrorInTeacher, SQLException e1) {
    }

    public TeacherDAOException(String errorUpdatingTeacher, Exception e) {
    }
}
