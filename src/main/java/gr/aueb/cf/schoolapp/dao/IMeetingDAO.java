package gr.aueb.cf.schoolapp.dao;



import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.model.Meeting;

import java.util.List;
import java.util.Optional;

public interface IMeetingDAO {

    Meeting insert(Meeting meeting) throws MeetingDAOException;

    Meeting update(Meeting meeting) throws MeetingDAOException;

    void delete(int id) throws MeetingDAOException;

    Optional<List<Meeting>> getByRoom(String room) throws MeetingDAOException;

    Meeting getById(int id) throws MeetingDAOException;
}
