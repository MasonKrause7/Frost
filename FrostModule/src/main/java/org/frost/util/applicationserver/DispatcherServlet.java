package org.frost.util.applicationserver;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.frost.util.ApplicationContainer;

import java.io.IOException;
import java.util.Map;

public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContainer applicationContainer = new ApplicationContainer();

        resp.getWriter().println("<html><body><h1>Dispatcher Servlet </h1></body></hmtl>");
        System.out.println(req.getRequestURI());
    }
}
