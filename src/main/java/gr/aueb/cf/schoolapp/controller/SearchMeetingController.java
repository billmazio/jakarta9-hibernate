package gr.aueb.cf.schoolapp.controller;



import gr.aueb.cf.schoolapp.dao.exceptions.MeetingDAOException;
import gr.aueb.cf.schoolapp.model.Meeting;
import gr.aueb.cf.schoolapp.service.IMeetingService;
import gr.aueb.cf.schoolapp.service.exceptions.MeetingNotFoundException;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.List;


@WebServlet("/schoolapp/searchMeeting")
public class SearchMeetingController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    private IMeetingService meetingService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/schoolapp/menu")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String room = request.getParameter("room").trim();



        try {
            List<Meeting> meetings = meetingService.getMeetingByRoom(room);
            if (meetings.isEmpty()) {
                request.setAttribute("meetingsNotFound", true);
                request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp")
                        .forward(request, response);
            }
            request.setAttribute("meetings", meetings);
            request.getRequestDispatcher("/school/static/templates/meetings.jsp")
                    .forward(request, response);
        } catch (MeetingDAOException | MeetingNotFoundException e) {
           String message = e.getMessage();
            request.setAttribute("sqlError", true);
            request.setAttribute("message", message);
            request.getRequestDispatcher("/school/static/templates/meetingsmenu.jsp")
                    .forward(request, response);
        }
    }
}
