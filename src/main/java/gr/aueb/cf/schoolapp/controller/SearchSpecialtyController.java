package gr.aueb.cf.schoolapp.controller;



import gr.aueb.cf.schoolapp.dao.exceptions.SpecialtyDAOException;
import gr.aueb.cf.schoolapp.model.Specialty;
import gr.aueb.cf.schoolapp.service.ISpecialtyService;
import gr.aueb.cf.schoolapp.service.exceptions.SpecialtyNotFoundException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;

@WebServlet("/schoolapp/searchSpecialty")
public class SearchSpecialtyController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private ISpecialtyService specialtyService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/menu")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name").trim();


        try {
            List<Specialty> specialties = specialtyService.getSpecialtiesBySpecialtyName(name);
            if (specialties.isEmpty()) {
                request.setAttribute("specialtyNotFound", true);
                request.getRequestDispatcher("/school/static/templates/specialtiesmenu.jsp")
                        .forward(request, response);
            }
            request.setAttribute("specialties", specialties);
            request.getRequestDispatcher("/school/static/templates/specialties.jsp").forward(request, response);
        } catch (SpecialtyDAOException | SpecialtyNotFoundException e) {
            String message = e.getMessage();
            request.setAttribute("sqlError", true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/school/static/templates/specialtiesmenu.jsp").forward(request, response);
        }
    }
}
