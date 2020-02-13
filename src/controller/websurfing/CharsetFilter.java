package controller.websurfing;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CharsetFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain next) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        if(!(path.endsWith(".css") || path.endsWith(".js") || path.endsWith(".jpg") || path.endsWith(".png"))){
            request.setCharacterEncoding("UTF-8");
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            next.doFilter(request, response);
        } else {
            next.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}