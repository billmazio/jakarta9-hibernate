package gr.aueb.cf.schoolapp.controller;


import gr.aueb.cf.schoolapp.dao.exceptions.SpecialtyDAOException;
import gr.aueb.cf.schoolapp.dto.SpecialtyDeleteDTO;
import gr.aueb.cf.schoolapp.service.ISpecialtyService;
import gr.aueb.cf.schoolapp.service.exceptions.SpecialtyNotFoundException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;



@WebServlet("/schoolapp/deleteSpecialty")
public class DeleteSpecialtyController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ISpecialtyService specialtyService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");

        SpecialtyDeleteDTO specialtyDTO = new SpecialtyDeleteDTO();
        specialtyDTO.setId(id);
        specialtyDTO.setName(name);

        try {
            specialtyService.deleteSpecialty(id);
            request.setAttribute("specialtyDTO", specialtyDTO);
            request.getRequestDispatcher("/school/static/templates/specialtyDeleted.jsp")
                    .forward(request, response);
        } catch (SpecialtyDAOException | SpecialtyNotFoundException e) {
            request.setAttribute("deleteAPIError", true);
            request.setAttribute("message", e.getMessage());
            request.getRequestDispatcher("/school/static/templates/specialties.jsp")
                    .forward(request, response);
        }
    }
}
