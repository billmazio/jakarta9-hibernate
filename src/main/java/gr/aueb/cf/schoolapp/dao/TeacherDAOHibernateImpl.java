package gr.aueb.cf.schoolapp.dao;

import gr.aueb.cf.schoolapp.dao.dbutil.HibernateHelper;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.model.Specialty;
import gr.aueb.cf.schoolapp.model.Teacher;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Dependent
public class TeacherDAOHibernateImpl implements ITeacherDAO {

    @Override
    public Teacher insert(Teacher teacher) throws TeacherDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            Specialty specialty = teacher.getSpecialty();
            if (specialty != null) {
                specialty.addTeacher(teacher);
            }

            entityManager.persist(teacher);
            HibernateHelper.commitTransaction();

            return teacher;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new TeacherDAOException("Error inserting teacher", e);
        }
    }

    @Override
    public Teacher update(Teacher teacher) throws TeacherDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

            Specialty specialty = teacher.getSpecialty();
            if (specialty != null) {
                specialty.addTeacher(teacher);
            }

            Teacher updatedTeacher = entityManager.merge(teacher);
            HibernateHelper.commitTransaction();

            return updatedTeacher;
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new TeacherDAOException("Error updating teacher", e);
        }
    }

    @Override
    public void delete(int id) throws TeacherDAOException {
        try {
            EntityManager entityManager = HibernateHelper.getEntityManager();
            HibernateHelper.beginTransaction();

             Teacher teacher = getById(id);
            if (teacher != null) {
                for (Meeting meeting : teacher.getMeetings()) {
                    meeting.setTeacher(null);
                }
                entityManager.remove(teacher);
            }
            HibernateHelper.commitTransaction();
        } catch (Exception e) {
            HibernateHelper.rollbackTransaction();
            throw new TeacherDAOException("Error deleting teacher", e);
        }
    }


    @Override
    public Optional<List<Teacher>> getByLastname(String lastname) throws TeacherDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<Teacher> query = entityManager.createQuery("FROM Teacher t WHERE t.lastname LIKE :lastname", Teacher.class);
        query.setParameter("lastname", lastname + "%");
        List<Teacher> teachers = query.getResultList();
        return teachers.isEmpty() ? Optional.empty() : Optional.of(teachers);
    }

    @Override
    public Teacher getById(int id) throws TeacherDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        return entityManager.find(Teacher.class, id);
    }

    @Override
    public List<Teacher> getAllTeachers() throws TeacherDAOException {
        EntityManager entityManager = HibernateHelper.getEntityManager();
        TypedQuery<Teacher> query = entityManager.createQuery("FROM Teacher", Teacher.class);
        return query.getResultList();
    }
}
