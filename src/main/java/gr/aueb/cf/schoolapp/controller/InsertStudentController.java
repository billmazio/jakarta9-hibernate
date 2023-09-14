package gr.aueb.cf.schoolapp.controller;




import gr.aueb.cf.schoolapp.dao.exceptions.CityDAOException;
import gr.aueb.cf.schoolapp.dto.StudentInsertDTO;
import gr.aueb.cf.schoolapp.model.City;
import gr.aueb.cf.schoolapp.model.Student;
import gr.aueb.cf.schoolapp.service.ICityService;
import gr.aueb.cf.schoolapp.service.IStudentService;
import gr.aueb.cf.schoolapp.service.exceptions.CityNotFoundException;
import gr.aueb.cf.schoolapp.validator.StudentValidator;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;




@WebServlet("/schoolapp/studentInsert")
public class InsertStudentController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private IStudentService studentService;

    @Inject
    private ICityService cityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<City> cities = cityService.getAllCities();
            request.setAttribute("cities", cities);
            request.getRequestDispatcher("/school/static/templates/studentsmenu.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "There was an error retrieving the list of cities.");
            request.getRequestDispatcher("/school/static/templates/studentsmenu.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String firstname = request.getParameter("firstname").trim();
        String lastname = request.getParameter("lastname").trim();
        String gender = request.getParameter("gender").trim();
        String birthdate = request.getParameter("birthdate").trim();

        // Check if any of the required fields are empty
        if (firstname.isEmpty() || lastname.isEmpty() || gender.isEmpty() || birthdate.isEmpty()) {
            request.setAttribute("error", "All fields are required.");
            forwardToStudentsMenu(request, response);
            return;
        }

        Date birthDateValue;
        try {
            birthDateValue = Date.valueOf(birthdate);
        } catch (IllegalArgumentException e) {
            request.setAttribute("error", "Invalid date format for Birthdate.");
            forwardToStudentsMenu(request, response);
            return;
        }

        City city;
        try {
            city = getCityFromRequest(request);
        } catch (CityNotFoundException e) {
            request.setAttribute("error", e.getMessage());
            forwardToStudentsMenu(request, response);
            return;
        }

        StudentInsertDTO studentInsertDTO = new StudentInsertDTO();
        studentInsertDTO.setFirstname(firstname);
        studentInsertDTO.setLastname(lastname);
        studentInsertDTO.setBirthdate(birthDateValue);
        studentInsertDTO.setGender(gender);
        studentInsertDTO.setCity(city);

        Map<String, String> errors = StudentValidator.validate(studentInsertDTO);
        if (!errors.isEmpty()) {
            request.setAttribute("error", errors);
            forwardToStudentsMenu(request, response);
            return;
        }

        try {
            Student student = studentService.insertStudent(studentInsertDTO);
            request.setAttribute("insertedStudent", student);
            request.getRequestDispatcher("/school/static/templates/studentInserted.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("sqlError", true);
            request.setAttribute("message", e.getMessage());
            forwardToStudentsMenu(request, response);
        }
    }


private City getCityFromRequest(HttpServletRequest request) throws CityNotFoundException {
    String cityId = request.getParameter("cityId"); // Assuming 'cityId' is the name attribute of your select dropdown
    if (cityId != null && !cityId.trim().isEmpty()) {
        try {
            Optional<City> cityOptional = Optional.ofNullable(cityService.getCityById(Integer.parseInt(cityId)));
            if (cityOptional.isPresent()) {
                return cityOptional.get();
            }
        } catch (NumberFormatException e) {
            throw new CityNotFoundException("Invalid city ID format.");
        } catch (CityDAOException e) {
            throw new CityNotFoundException("City not found for provided ID.");
        }
    }
    throw new CityNotFoundException("City ID not provided or is empty.");
}







    private void forwardToStudentsMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<City> cities = cityService.getAllCities();
            request.setAttribute("cities", cities);
            request.getRequestDispatcher("/school/static/templates/studentsmenu.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "There was an error retrieving the list of cities.");
            request.getRequestDispatcher("/school/static/templates/studentsmenu.jsp").forward(request, response);
        }
    }

//    @Override
//    public void destroy() {
//        entityManager.close();
//        emf.close();
//    }
}
