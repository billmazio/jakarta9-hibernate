package gr.aueb.cf.schoolapp.controller;



import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.exceptions.CityNotFoundException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



import java.io.IOException;
import java.util.List;

@WebServlet("/schoolapp/searchCity")
public class SearchCitiesController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

//        request.setAttribute("isError", false);
//        request.setAttribute("error", "");
//        request.setAttribute("teachersNotFound", false);
        request.getRequestDispatcher("/schoolapp/menu")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String name = request.getParameter("name").trim();


        try {
            List<City> cities = cityService.getCitiesByCityName(name);
            if (cities.isEmpty()) {
                request.setAttribute("citiesNotFound", true);
                request.getRequestDispatcher("/school/static/templates/citiesmenu.jsp")
                        .forward(request, response);
            } else {
                request.setAttribute("cities", cities);
                request.getRequestDispatcher("/school/static/templates/cities.jsp").forward(request, response);
            }
        } catch (CityDAOException | CityNotFoundException e) {
            String message = e.getMessage();
            request.setAttribute("sqlError", true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/school/static/templates/citiesmenu.jsp").forward(request, response);


        }
    }
}
