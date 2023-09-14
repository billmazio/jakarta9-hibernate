package gr.aueb.cf.schoolapp.service;



import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapp.dto.MeetingUpdateDTO;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.service.exceptions.MeetingNotFoundException;

import java.util.List;

public interface IMeetingService {
    Meeting insertMeeting(MeetingInsertDTO dto) throws MeetingDAOException;
    Meeting updateMeeting(MeetingUpdateDTO dto) throws MeetingDAOException, MeetingNotFoundException;
    void deleteMeeting(int id) throws MeetingDAOException, MeetingNotFoundException;
    List<Meeting> getMeetingByRoom(String room) throws MeetingDAOException, MeetingNotFoundException;
    Meeting getMeetingById(int id) throws MeetingDAOException, MeetingNotFoundException;
}
