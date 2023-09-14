package gr.aueb.cf.schoolapp.dao.exceptions;

public class StudentDAOException extends Exception {
    private static final long serialVersionUID = 123456L;
    public StudentDAOException(String s) { super(s); }

    public StudentDAOException(String errorUpdatingStudent, Exception e) {
    }
}
