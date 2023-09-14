package gr.aueb.cf.schoolapp.controller;






import gr.aueb.cf.schoolapp.authentication.AuthenticationProvider;
import gr.aueb.cf.schoolapp.dto.UserLoginDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


import java.io.IOException;



@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String isError = request.getParameter("isError");

        if (isError != null && isError.equals("true")) {
            request.setAttribute("isError", isError);
        } else {
            request.setAttribute("isError", "false");
        }

        request.getRequestDispatcher("/school/static/templates/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
//
        UserLoginDTO userLoginDTO = new UserLoginDTO(username, password);
        boolean principleIsAuthenticated = AuthenticationProvider.authenticate(userLoginDTO);



        if (principleIsAuthenticated) {
            HttpSession session = request.getSession(false);
            session.setAttribute("loginName", username);
            response.sendRedirect(request.getContextPath() + "/schoolapp/menu");
        } else {
            response.sendRedirect(request.getContextPath() + "/login?isError=true");
        }

        }
    }

