package org.frost.util.applicationserver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.frost.util.ApplicationContainer;
import org.frost.util.URLResolver;

import java.io.IOException;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {
    private URLResolver resolver = new URLResolver();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(req.getRequestURI());

        resp.getWriter().println("<html><body><h1>" + resolver.getRequest(req.getRequestURI()) + "</h1></body></hmtl>");

    }


}
