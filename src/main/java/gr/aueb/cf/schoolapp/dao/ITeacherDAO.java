package gr.aueb.cf.schoolapp.dao;



import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;

import java.util.List;
import java.util.Optional;

public interface ITeacherDAO {
    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(int id) throws TeacherDAOException;
    Optional<List<Teacher>> getByLastname(String lastname) throws TeacherDAOException;
    Teacher getById(int id) throws TeacherDAOException;

    List<Teacher> getAllTeachers() throws TeacherDAOException;
}
