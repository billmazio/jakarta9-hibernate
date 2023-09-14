
package gr.aueb.cf.schoolapp.filter;



import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        boolean authenticated = false;

        String requestedURI = req.getRequestURI();
//        if (requestedURI.endsWith(".css")) {
//            chain.doFilter(req, response);
//        } else {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("JSESSIONID")) {
                    HttpSession session = req.getSession(false);
//                        System.out.println(session.getId());
//                        System.out.println(session.getAttribute("loginName"));
                    authenticated = (session != null) && (session.getAttribute("loginName") != null);
                }
            }
        }
        //  }

        if (authenticated) {
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
