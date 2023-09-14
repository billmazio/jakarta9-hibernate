package gr.aueb.cf.schoolapp.controller;





import gr.aueb.cf.schoolapp.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolapp.model.Teacher;
import gr.aueb.cf.schoolapp.service.ITeacherService;
import gr.aueb.cf.schoolapp.service.exceptions.TeacherNotFoundException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/schoolapp/searchTeacher")
public class SearchTeachersController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ITeacherService teacherService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.getRequestDispatcher("/schoolapp/menu").forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String lastname = request.getParameter("lastname").trim();

        try {
            List<Teacher> teachers = teacherService.getTeachersByLastname(lastname);
            if (teachers.isEmpty()) {
                request.setAttribute("teachersNotFound", true);
                request.getRequestDispatcher("/school/static/templates/teachersmenu.jsp").forward(request, response);
            } else {
                request.setAttribute("teachers", teachers);
                request.getRequestDispatcher("/school/static/templates/teachers.jsp").forward(request, response);
            }
        } catch (TeacherNotFoundException | TeacherDAOException e) {
            String message = e.getMessage();
            request.setAttribute("sqlError", true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/school/static/templates/teachersmenu.jsp").forward(request, response);
        }
    }
}
