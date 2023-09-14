package gr.aueb.cf.schoolapp.controller;


import gr.aueb.cf.schoolapp.dao.exceptions.StudentDAOException;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.IStudentService;

import gr.aueb.cf.schoolapp.service.exceptions.StudentNotFoundException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;
@WebServlet("/schoolapp/searchStudent")
public class SearchStudentsController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private IStudentService studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        // Handle GET requests here, if needed.
        // You can show a search form or redirect to another page.
        response.sendRedirect(request.getContextPath() + "/schoolapp/menu");
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String lastname = request.getParameter("lastname").trim();

        try {
            List<Student> students = studentService.getStudentsByLastname(lastname);
            if (students.isEmpty()) {
                request.setAttribute("studentsNotFound", true);
                request.getRequestDispatcher("/school/static/templates/studentsmenu.jsp").forward(request, response);
            } else {
                request.setAttribute("students", students);
                request.getRequestDispatcher("/school/static/templates/students.jsp").forward(request, response);
            }
        } catch (StudentNotFoundException | StudentDAOException e) {
            // Handle the exception appropriately, e.g., show an error message.
            String message = e.getMessage();
            request.setAttribute("sqlError", true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/school/static/templates/studentsmenu.jsp").forward(request, response);
        }

    }
}
