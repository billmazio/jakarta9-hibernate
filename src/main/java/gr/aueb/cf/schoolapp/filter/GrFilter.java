package gr.aueb.cf.schoolapp.filter;



import jakarta.servlet.*;

import java.io.IOException;

public class GrFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }
}
