package gr.aueb.cf.schoolapp.dao;



import gr.aueb.cf.schoolapp.dao.dbutil.HibernateHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.model.Student;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;


import javax.ws.rs.ext.Provider;
import java.util.List;
import java.util.Optional;
@Dependent
public class StudentDAOHibernateImpl implements IStudentDAO {


    @Override
    public Student insert(Student student) throws StudentDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            City city = student.getCity();
            if (city != null) {
                city.addStudent(student);
            }

            entityManager.persist(student);
            HibernateHelper.commitTransaction();

            return student;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new StudentDAOException("Error inserting student", e);
        }
    }

    @Override
    public Student update(Student student) throws StudentDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            City city = student.getCity();
            if (city != null) {
                city.addStudent(student);  // Using convenience method
            }

            Student updatedStudent = entityManager.merge(student);
            HibernateHelper.commitTransaction();

            return updatedStudent;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new StudentDAOException("Error updating student", e);
        }
    }

    @Override
    public void delete(int id) throws StudentDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            Student student = getById(id);
            if (student != null) {
                for (Meeting meeting : student.getMeetings()) {
                    meeting.setStudent(null);
                }
                entityManager.remove(student);
            }
            HibernateHelper.commitTransaction();
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new StudentDAOException("Error deleting student", e);
        }
    }

    @Override
    public Optional<List<Student>> getByLastname(String lastname) throws StudentDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("FROM Student s WHERE s.lastname LIKE :lastname", Student.class);
        query.setParameter("lastname", lastname + "%");

        List<Student> students = query.getResultList();
        return students.isEmpty() ? Optional.empty() : Optional.of(students);
    }

    @Override
    public Student getById(int id) throws StudentDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        EntityManager entityManager = HibernateHelper.getEntityManager();

        TypedQuery<Student> query = entityManager.createQuery("FROM Student", Student.class);
        return query.getResultList();
    }
}
