package org.frost.util.applicationserver;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.frost.util.ApplicationContainer;
import org.frost.util.URLResolver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * will handle and forward all request to proper objects to invoke methods to get data
 */
public class DispatcherServlet extends HttpServlet {

    private URLResolver resolver = new URLResolver();

    /**
     *
     * @param request  an {@link HttpServletRequest} object that
     *                  contains the request the client has made
     *                  of the servlet
     *
     * @param response an {@link HttpServletResponse} object that
     *                  contains the response the servlet sends
     *                  to the client
     *
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(request.getRequestURI().equals("/favicon.ico")) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        } else {
            PrintWriter writer = response.getWriter();//used to wrap response in character streams
            response.setContentType("application/json");//content type must be set in order for browser to accept response
            response.addHeader("Access-Control-Allow-Origin", "*");//enable cors so all origins can access resource
            response.setCharacterEncoding("UTF-8");
            writer.print(resolver.getRequest(request.getRequestURI()));//wraps response in character stream
        }





    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BufferedReader g = request.getReader();
        String json = g.lines().collect(Collectors.joining());
        try {
            resolver.postRequest(json,request.getRequestURI());
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
