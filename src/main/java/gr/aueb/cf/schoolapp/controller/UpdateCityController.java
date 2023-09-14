package gr.aueb.cf.schoolapp.controller;




import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dto.CityUpdateDTO;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.exceptions.CityNotFoundException;
import gr.aueb.cf.schoolapp.validator.CityValidator;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.Map;

@WebServlet("/schoolapp/updateCity")
public class UpdateCityController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ICityService cityService;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/school/static/templates/cityUpdate.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");


        CityUpdateDTO newCityDTO = new CityUpdateDTO();
        newCityDTO.setId(id);
        newCityDTO.setName(name);
        request.setAttribute("updatedCity", newCityDTO);


        try {
            Map<String, String > errors = CityValidator.validate(newCityDTO);

            if (!errors.isEmpty()) {
                String nameMessage = (errors.get("name") != null) ? "City Name: " + errors.get("name") : "";
                request.setAttribute("error", nameMessage);
                request.getRequestDispatcher("/school/static/templates/citiesmenu.jsp")
                        .forward(request, response);
            }


            City city =  cityService.updateCity(newCityDTO);
            request.setAttribute("message", "");
            request.setAttribute("city", city);
            request.getRequestDispatcher("/school/static/templates/cityUpdated.jsp")
                    .forward(request, response);
        } catch (CityNotFoundException | CityDAOException e) {
            String message = e.getMessage();
            request.setAttribute("message", message);
            request.getRequestDispatcher("/schoolapp/static/templates/cityUpdated.jsp")
                    .forward(request, response);

        }
    }
}
