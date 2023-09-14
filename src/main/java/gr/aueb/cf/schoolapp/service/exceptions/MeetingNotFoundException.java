package gr.aueb.cf.schoolapp.service.exceptions;


import gr.aueb.cf.schoolapp.model.Meeting;

public class MeetingNotFoundException extends Exception{

    public MeetingNotFoundException(Meeting meeting) {
        super("Meeting with id: " + meeting.getId() + " does not exist");
    }

    public MeetingNotFoundException(String s) {super(s);}
}
