package gr.aueb.cf.schoolapp.dao;


import gr.aueb.cf.schoolapp.dao.dbutil.HibernateHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

@Dependent
public class MeetingDAOHibernateImpl implements IMeetingDAO {



    @Override
    public Optional<List<Meeting>> getByRoom(String room) {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<Meeting> query = entityManager.createQuery("FROM Meeting m WHERE m.room LIKE :room", Meeting.class);
        query.setParameter("room", room + "%");
        List<Meeting> meetings = query.getResultList();
        return meetings.isEmpty() ? Optional.empty() : Optional.of(meetings);
    }

    @Override
    public Meeting getById(int id) throws MeetingDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        return entityManager.find(Meeting.class, id);
    }

    @Override
    public Meeting insert(Meeting meeting) throws MeetingDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            Teacher teacher = meeting.getTeacher();
            Student student = meeting.getStudent();

            if (teacher != null) {
                teacher.addMeeting(meeting);  // Use the convenience method
            }

            if (student != null) {
                student.addMeeting(meeting);  // Use the convenience method
            }

            entityManager.persist(meeting);
            HibernateHelper.commitTransaction();
            return meeting;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new MeetingDAOException("Error inserting meeting", e);
        }
    }

    @Override
    public Meeting update(Meeting meeting) throws MeetingDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            // Existing Teacher and Student can be fetched and their convenience methods could be used for removing old meetings
            // But let's assume the update doesn't affect the associated Teacher and Student

            Meeting updatedMeeting = entityManager.merge(meeting);

            HibernateHelper.commitTransaction();
            return updatedMeeting;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new MeetingDAOException("Error updating meeting", e);
        }
    }

    @Override
    public void delete(int id) throws MeetingDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            Meeting meeting = getById(id);

            if (meeting != null) {
                Teacher teacher = meeting.getTeacher();
                Student student = meeting.getStudent();

                if (teacher != null) {
                    teacher.removeMeeting(meeting);  // Use the convenience method
                }

                if (student != null) {
                    student.removeMeeting(meeting);  // Use the convenience method
                }

                entityManager.remove(meeting);
            } else {
                throw new MeetingDAOException("Meeting with ID: " + id + " not found");
            }

            HibernateHelper.commitTransaction();
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new MeetingDAOException("Error deleting meeting", e);
        }
    }

}
