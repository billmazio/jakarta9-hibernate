package gr.aueb.cf.schoolapp.service;


import gr.aueb.cf.schoolapp.dao.ITeacherDAO;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.TeacherInsertDTO;
import gr.aueb.cf.schoolapp.dto.TeacherUpdateDTO;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TeacherServiceImpl implements ITeacherService {
    @Inject
    private ITeacherDAO teacherDAO;



    @Override
    public Teacher insertTeacher(TeacherInsertDTO dto) throws TeacherDAOException {
        Teacher teacher = new Teacher();
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());
        // You might need to retrieve the Specialty object associated with the teacher and set it here.
        teacher.setSpecialty(dto.getSpecialty());  // Assuming that Specialty object is part of your DTO.

        return teacherDAO.insert(teacher);
    }


    @Override
    @Transactional
    public Teacher updateTeacher(TeacherUpdateDTO dto) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher = teacherDAO.getById(dto.getId());
        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher not found.");
        }
        teacher.setFirstname(dto.getFirstname());
        teacher.setLastname(dto.getLastname());
        teacher.setSpecialty(dto.getSpecialty());  // Assuming that Specialty object is part of your DTO.

        return teacherDAO.update(teacher);
    }


    @Override
    public void deleteTeacher(int id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher = teacherDAO.getById(id);
        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher not found.");
        }
        teacherDAO.delete(id);
    }

    @Override
    public Teacher getTeacherById(int id) throws TeacherDAOException, TeacherNotFoundException {
        Teacher teacher = teacherDAO.getById(id);
        if (teacher == null) {
            throw new TeacherNotFoundException("Teacher not found.");
        }
        return teacher;
    }

    @Override
    public List<Teacher> getAllTeachers() throws TeacherDAOException, TeacherNotFoundException {
        List<Teacher> teachers = teacherDAO.getAllTeachers();
        if (teachers == null || teachers.isEmpty()) {
            throw new TeacherNotFoundException("No teachers found.");
        }
        return teachers;
    }

    @Override
    public List<Teacher> getTeachersByLastname(String lastname) throws TeacherDAOException, TeacherNotFoundException {
        Optional<List<Teacher>> teachersOpt = teacherDAO.getByLastname(lastname);
        if (teachersOpt.isEmpty()) {
            throw new TeacherNotFoundException("No teacher found with the given lastname.");
        }
        return teachersOpt.get();
    }


}
