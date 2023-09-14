package gr.aueb.cf.schoolapp.dao.exceptions;

public class MeetingDAOException extends Exception{
    private static final long serialVersionUID = 123456L;

    public MeetingDAOException(String s) { super(s); }

    public MeetingDAOException(String errorInsertingMeeting, Exception e) {
    }
}
