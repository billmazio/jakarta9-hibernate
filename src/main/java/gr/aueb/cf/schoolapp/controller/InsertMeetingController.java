
package gr.aueb.cf.schoolapp.controller;



import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.dto.MeetingInsertDTO;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.IMeetingService;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import gr.aueb.cf.schoolapp.validator.MeetingValidator;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/schoolapp/meetingInsert")
public class InsertMeetingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private IMeetingService meetingService;

    @Inject
    private ITeacherService teacherService;

    @Inject
    private IStudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();
            List<Student> students = studentService.getAllStudents();

            request.setAttribute("teachers", teachers);
            request.setAttribute("students", students);

            request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp").forward(request, response);
        } catch (TeacherDAOException e) {
            request.setAttribute("error1", "Error while retrieving the list of teachers");
            request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp").forward(request, response);
        } catch (TeacherNotFoundException e) {
            request.setAttribute("error1", "No teachers found in the database");
            request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp").forward(request, response);
        } catch (StudentDAOException e) {
            request.setAttribute("error2", "Error while retrieving the list of students");
            request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp").forward(request, response);
        } catch (StudentNotFoundException e) {
            request.setAttribute("error2", "No students found in the database");
            request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String room = request.getParameter("room");
        String meetingDate = request.getParameter("meetingDate");



        Teacher teacher;
        teacher = getTeacherFromRequest(request);
        if (teacher == null) {
            request.setAttribute("error", "Invalid teacher ID");
            reloadTeachersAndStudentsAndForward(request, response);
            return;
        }

        Student student;
        student = getStudentFromRequest(request);
        if (student == null) {
            request.setAttribute("error", "Invalid student ID");
            reloadTeachersAndStudentsAndForward(request, response);
            return;
        }


        if (meetingDate == null || !meetingDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
            request.setAttribute("error", "Invalid format for Meeting Date. Please use yyyy-mm-dd format.");
            reloadTeachersAndStudentsAndForward(request, response);
            return;
        }

        // Create the MeetingInsertDTO.
        MeetingInsertDTO meetingInsertDTO = new MeetingInsertDTO();
        meetingInsertDTO.setRoom(room);
        meetingInsertDTO.setMeetingDate(Date.valueOf(meetingDate));
        meetingInsertDTO.setStudent(student);
        meetingInsertDTO.setTeacher(teacher);


        Map<String, String> errors = MeetingValidator.validate(meetingInsertDTO);

        if (!errors.isEmpty()) {
            request.setAttribute("error", errors);
            reloadTeachersAndStudentsAndForward(request, response);
            return;
        }


        try {
          Meeting meeting = meetingService.insertMeeting(meetingInsertDTO);
            request.setAttribute("insertedMeeting", meeting);
            request.getRequestDispatcher("/school/static/templates/meetingInserted.jsp").forward(request, response);

        } catch (Exception e) {
            request.setAttribute("sqlError", true);
            request.setAttribute("message", e.getMessage());
            reloadTeachersAndStudentsAndForward (request, response);
        }
    }

    private Teacher getTeacherFromRequest(HttpServletRequest request) {
        String teacherId = request.getParameter("teacherId");
        if (teacherId != null && !teacherId.trim().isEmpty()) {
            try {

                return teacherService.getTeacherById(Integer.parseInt(teacherId));
            } catch (NumberFormatException | TeacherNotFoundException e) {
                e.printStackTrace();
            } catch (TeacherDAOException e) {

            }
        }
        return null;
    }

    private Student getStudentFromRequest(HttpServletRequest request) {
        String studentId = request.getParameter("studentId");
        if (studentId != null && !studentId.trim().isEmpty()) {
            try {

                return studentService.getStudentById(Integer.parseInt(studentId));
            } catch (NumberFormatException | StudentNotFoundException e) {
                e.printStackTrace();
            } catch (StudentDAOException e) {

            }
        }
        return null;
    }

    private void reloadTeachersAndStudentsAndForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Teacher> teachers = teacherService.getAllTeachers();
            request.setAttribute("teachers", teachers);
        } catch (TeacherDAOException | TeacherNotFoundException e) {
            request.setAttribute("error", e instanceof TeacherDAOException ? "Error while retrieving the list of teachers" : "No teachers found in the database");
        }

        try {
            List<Student> students = studentService.getAllStudents();
            request.setAttribute("students", students);
        } catch (StudentDAOException | StudentNotFoundException e) {
            request.setAttribute("error", e instanceof StudentDAOException ? "Error while retrieving the list of students" : "No students found in the database");
        }

        request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp").forward(request, response);
    }



}
