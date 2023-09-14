
package gr.aueb.cf.schoolapp.controller;



import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dto.CityInsertDTO;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.validator.CityValidator;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Map;

@WebServlet("/schoolapp/cityInsert")
public class InsertCityController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    private ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/menu").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("error", "");

        String name = request.getParameter("name").trim();

        // Create the CityInsertDTO object
        CityInsertDTO cityInsertDTO = new CityInsertDTO();
        cityInsertDTO.setName(name);

        // Perform validation
        Map<String, String> errors = CityValidator.validate(cityInsertDTO);

        // If there are validation errors, set them in the request and forward back to the form page
        if (!errors.isEmpty()) {
            String cityNameMessage = errors.get("name");
            request.setAttribute("error", cityNameMessage);
            request.getRequestDispatcher("/school/static/templates/citiesmenu.jsp").forward(request, response);
        } else {
            // If no errors, proceed with the insert operation and display success message
            try {
                City city = cityService.insertCity(cityInsertDTO);
                request.setAttribute("insertedCity", city);
                request.getRequestDispatcher("/school/static/templates/cityInserted.jsp").forward(request, response);
            } catch (CityDAOException e) {
                // Handle database-related errors
                request.setAttribute("sqlError", true);
                request.setAttribute("message", e.getMessage());
                request.getRequestDispatcher("/schoolapp/menu").forward(request, response);
            }
        }
    }
}
