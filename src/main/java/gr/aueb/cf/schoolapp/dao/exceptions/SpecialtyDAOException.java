package gr.aueb.cf.schoolapp.dao.exceptions;

public class SpecialtyDAOException extends Exception{

    public SpecialtyDAOException(String s) {
        super(s);
    }

    public SpecialtyDAOException(String errorUpdatingSpecialty, Exception e) {
    }
}
